package edu.trainee.web;

import edu.trainee.domain.Airplane;
import edu.trainee.domain.City;
import edu.trainee.domain.Flight;
import edu.trainee.domain.Region;
import edu.trainee.logic.CurrentDate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dennis on 9/22/2015.
 */

@Controller
public class AdminController extends AbstractController {

    @RequestMapping(value = "admin/cities", method = RequestMethod.GET)
    public String viewCityPanelPage(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("message", "Hello from testController!");
        model.addAttribute("helloName", name);
        System.out.println();
        return "admin/cities";
    }

    @RequestMapping(value = "admin/addcities", method = RequestMethod.POST)
    public String addCities(Model model,
                            @RequestParam(value = "lines", required = false) String lines) {
        String matchCorrectLine = "([А-Яа-яёіїє']+(?: |-)?[А-Яа-яёіїє']+?)(?:[^А-Яа-я]+)([А-Яа-яёіїє' -]+)";
        Pattern pattern = Pattern.compile(matchCorrectLine);
        Matcher matcher = pattern.matcher(lines);
        Set<String> set = new TreeSet<>();
//        System.out.println("Set size before: " + set.size());
        while (matcher.find()) {
            set.add(matcher.group(1) + ":" + matcher.group(2));
        }

        Iterator<String> iterator = set.iterator();

        while (iterator.hasNext()) {
            Matcher matcher1 = pattern.matcher(iterator.next());
            while (matcher1.find()) {
                if (regionService.isExisting(matcher1.group(2))) {
                    Region region = regionService.getRegionByName(matcher1.group(2));
                    cityService.save(new City(matcher1.group(1), region));

                } else {
                    regionService.save(new Region(matcher1.group(2)));
                    cityService.save(new City(matcher1.group(1), regionService.getRegionByName(matcher1.group(2))));
                }
            }

        }
        return "redirect:cities";
    }

    @RequestMapping(value = "admin/addairplane", method = RequestMethod.POST)
    public String addAirplane(Model model, @ModelAttribute Airplane airplane) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        airplaneService.save(airplane);
        return "redirect:airplanes";
    }


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String viewAdminPanelPage(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("airplanes", airplaneService.getFreeAirplanes());
        model.addAttribute("cities", cityService.getAllCities());
        return "admin";
    }

    @RequestMapping(value = "admin/airplanes", method = RequestMethod.GET)
    public String viewAirplanePanelPage(Model model, @RequestParam(value = "page", required = false) Integer id) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("airplanes", airplaneService.getAllAirplanes());

        Long numberOfAirplanes = airplaneService.getNumberOfAirplanes();
        int pageSize = 5;
        int tail = 0;
        if (numberOfAirplanes % pageSize > 0) tail = 1;
        int maxPage = (int) ((numberOfAirplanes / pageSize) + tail);

        model.addAttribute("pages", maxPage);

        if (id != null) {
            model.addAttribute("airplanes", airplaneService.getResultPerPage(id, pageSize));
        } else model.addAttribute("airplanes", airplaneService.getResultPerPage(1, pageSize));


        return "admin/airplanes";
    }

    @RequestMapping(value = "addflight", method = RequestMethod.POST)
    public String addFlight(Model model,
                            @RequestParam(value = "airplane") Airplane airplane,
                            @RequestParam(value = "fromcity") City cityFrom,
                            @RequestParam(value = "tocity") City cityTo,
                            @RequestParam(value = "date") String dateFromForm,
                            @RequestParam(value = "price") String price) {

        Flight flight = new Flight();
        Date currentDate = CurrentDate.getCurrentDate();
        Date setDate = null;
        BigDecimal fixedPrice = null;
        String matchCorrectLine = "([-+]?[0-9]*)(\\.?[0-9]+)";


        Pattern pattern = Pattern.compile(matchCorrectLine);
        Matcher matcher = pattern.matcher(price);

        if (matcher.find()) {
            if (matcher.group(2).length() > 3) {
                model.addAttribute("error", "invalid number format");
                return viewAdminPanelPage(model);
            } else {
                fixedPrice = new BigDecimal(price);
            }
        }

        try {
            setDate = new SimpleDateFormat("yy-MM-dd HH:mm").parse(dateFromForm);
        } catch (ParseException ea) {
            try {
                setDate = new SimpleDateFormat("yy-MM-dd").parse(dateFromForm);
            } catch (ParseException e) {
                System.out.println(e);
                model.addAttribute("error", "incorrect Date format");
                return viewAdminPanelPage(model);
            }
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(setDate);

        if (cityFrom.equals(cityTo)) {
            model.addAttribute("error", "cities match");
            return viewAdminPanelPage(model);
        } else if (currentDate.compareTo(setDate) >= 0) {
            model.addAttribute("error", "flight date < current date");
            return viewAdminPanelPage(model);
        } else {
            flight.setFlightTime(calendar);
            flight.setFlyFromCity(cityFrom);
            flight.setFlyToCity(cityTo);
            flight.setInitPrice(fixedPrice);
            flight.setSeats(new ArrayList<>(airplane.getNumOfSeats()));
            flight.setAirplane(airplane);
            flight.setFlightCreationTime(Calendar.getInstance());
        }

        flightService.save(flight);

        model.addAttribute("msg", "flight has been added successfully");
        return viewAdminPanelPage(model);
    }


}
