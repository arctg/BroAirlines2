package edu.trainee.web;

import edu.trainee.domain.City;
import edu.trainee.domain.Flight;
import edu.trainee.domain.Order;
import edu.trainee.domain.User;
import edu.trainee.logic.CurrentDate;
import edu.trainee.logic.PriceFixer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dennis on 9/23/2015.
 */
@Controller
@Scope("request")
public class UserController extends AbstractController {

    @Autowired
    private Cart cart;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String viewMainPage(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("flights", flightService.get10Nearest(CurrentDate.getCurrentDate()));
        for (Flight flight: flightService.get10Nearest(CurrentDate.getCurrentDate())){
            System.out.println(flight.getSeats());
        }
        System.out.println("Cart is:" + cart.getFlightId());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String now = format.format(CurrentDate.getCurrentDate().getTime());
        model.addAttribute("now", now);

        return "main";
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/cabinet",method = RequestMethod.GET)
    public String viewCabinetPage(Model model){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userService.getUserByEmail(name).getId());


        model.addAttribute("nearOrders",orderService.getNearest(CurrentDate.getCurrentDate(),userService.getUserByEmail(name).getId()));
        model.addAttribute("pastOrders",orderService.getPast(CurrentDate.getCurrentDate(), userService.getUserByEmail(name).getId()));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String now = format.format(CurrentDate.getCurrentDate().getTime());
        model.addAttribute("now", now);

        return "cabinet";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewRootPage(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("cities", cityService.getAllCities());
        return "redirect:main";
    }

    @RequestMapping(value = "findflight", method = RequestMethod.GET)
    public String findFlights(Model model,
                              @RequestParam(value = "fromcity", required = true) City fromCity,
                              @RequestParam(value = "tocity", required = true) City toCity,
                              @RequestParam(value = "begindate", required = true) String beginDateString,
                              @RequestParam(value = "enddate", required = true) String endDateString
    ) {

        Date beginDate = null;
        Date endDate = null;
        Calendar beginDateCal = new GregorianCalendar();
        Calendar endDateCal = new GregorianCalendar();

        if (fromCity.equals(toCity)) {
            model.addAttribute("error", "cities match");
            return viewMainPage(model);
        }

        try {
            beginDate = new SimpleDateFormat("yy-MM-dd HH:mm").parse(beginDateString);
            endDate = new SimpleDateFormat("yy-MM-dd HH:mm").parse(endDateString);
            beginDateCal.setTime(beginDate);
            endDateCal.setTime(endDate);

        } catch (ParseException ea) {
            try {
                beginDate = new SimpleDateFormat("yy-MM-dd").parse(beginDateString);
                endDate = new SimpleDateFormat("yy-MM-dd").parse(endDateString);
                beginDateCal.setTime(beginDate);
                endDateCal.setTime(endDate);
            } catch (ParseException e) {
                model.addAttribute("error", "incorrect Date format");
                return viewMainPage(model);
            }
        }

        if (CurrentDate.getCurrentDate().compareTo(endDateCal) > 0) {
            model.addAttribute("error", "Time range you entered is incorrect");
            return viewMainPage(model);
        }
        if (CurrentDate.getCurrentDate().compareTo(beginDateCal) > 0) {
            beginDateCal = CurrentDate.getCurrentDate();
        }

        model.addAttribute("result", flightService.find(fromCity, toCity, beginDateCal, endDateCal));
        model.addAttribute("daterangefrom", beginDateString);
        model.addAttribute("daterangeto", endDateString);

        return viewMainPage(model);
    }

    @RequestMapping(value = "placeanorder")
    public String placeAnOrder(Model model, @RequestParam(value = "id") String id) {

        Map<String, BigDecimal> services = new HashMap<>();
        services.put("baggage", PriceFixer.BAGGAGE);
        services.put("priboarding", PriceFixer.PRIORITY_BOARDING);
        cart.setFlightId(flightService.getFlightById(Long.parseLong(id)).getId());

        model.addAttribute("services", services);
        model.addAttribute("cart", cart);
        model.addAttribute("flight",flightService.getFlightById(cart.getFlightId()));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String now = format.format(CurrentDate.getCurrentDate().getTime());
        model.addAttribute("now", now);

        return "result";
    }

    @RequestMapping(value = "confirm")
    public String confirmOrder(Model model,
                               @RequestParam(value = "baggage", required = false) Boolean baggage,
                               @RequestParam(value = "priboarding", required = false) Boolean priorityBoarding) {


        if (baggage == null) {
            baggage = false;
            cart.setBaggage(BigDecimal.ZERO);
        } else cart.setBaggage(PriceFixer.BAGGAGE);

        if (priorityBoarding == null) {
            priorityBoarding = false;
            cart.setPriorityBoarding(BigDecimal.ZERO);
        } else cart.setPriorityBoarding(PriceFixer.PRIORITY_BOARDING);


        model.addAttribute("cart", cart);
        model.addAttribute("flight",flightService.getFlightById(cart.getFlightId()));


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String now = format.format(CurrentDate.getCurrentDate().getTime());
        model.addAttribute("now", now);


        return "payment";

    }

    @Secured(value = "ROLE_USER")
    @RequestMapping(value = "pay")
    public String pay(Model model) {
        if (flightService.hasFreeSeats(flightService.getFlightById(cart.getFlightId()))) {
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
            User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            System.out.println(user);
            Flight flight = flightService.getFlightById(cart.getFlightId());
            System.out.println("Seats of flight: " + flight.getSeats().size());
            Long seat = null;

            if (flight.getSeats().isEmpty()) {
                System.out.println(flight.getSeats());
                seat = 0L;
                flight.getSeats().add(user.getId());
            } else {
                System.out.println(flight.getSeats());
                System.out.println(flight.getSeats().size());
                for (int i = 0; i < flight.getSeats().size(); i++) {
                    if (flight.getSeats().get(i) == null) {
                        flight.getSeats().set(i, user.getId());
                        seat = (long)i;
                        break;
                    } else if (flight.getSeats().size()<flight.getAirplane().getNumOfSeats()){
                        flight.getSeats().add(user.getId());
                        seat = (long)flight.getSeats().lastIndexOf(user.getId());
                        break;
                    }
                }
            }




            Order order = new Order(
                    flight,
                    user,
                    cart.getBaggage() != BigDecimal.ZERO,
                    cart.getPriorityBoarding() != BigDecimal.ZERO,
                    flight.getTempPrice().add(cart.getPriorityBoarding()).add(cart.getBaggage().add(flightService.getExtra(flight.getId()))),
                    seat,
                    CurrentDate.getCurrentDate());

            flight.setTempPrice(flight.getInitPrice());
            flightService.save(flight);



            System.out.println(order);
            orderService.save(order);

            cart=null;
        }


        return "redirect:main";
    }


}
