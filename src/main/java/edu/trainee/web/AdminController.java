package edu.trainee.web;

import edu.trainee.domain.Airplane;
import edu.trainee.domain.City;
import edu.trainee.domain.Flight;
import edu.trainee.domain.Region;
import edu.trainee.logic.CurrentDate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String now = format.format(CurrentDate.getCurrentDate().getTime());
        model.addAttribute("now", now);

        return "admin/cities";
    }

    @Secured(value = "ROLE_ADMIN")
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

    @Secured(value = "ROLE_ADMIN")
    @RequestMapping(value = "admin/addairplane", method = RequestMethod.POST)
    public String addAirplane(Model model, @ModelAttribute Airplane airplane) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        airplane.setOperable(true);
        airplaneService.save(airplane);
        return "redirect:airplanes";
    }

    @Secured(value = "ROLE_ADMIN")
    @RequestMapping(value = "admin/changeairplanestate", method = RequestMethod.POST)
    public String changeAirplaneState(Model model, @RequestParam(value = "id") Airplane airplane) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        airplane.setOperable(!airplane.isOperable());
        airplaneService.save(airplane);
        return "redirect:airplanes";
    }


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String viewAdminPanelPage(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Airplane> airplaneList = airplaneService.getFreeAirplaness(Calendar.getInstance());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String now = format.format(CurrentDate.getCurrentDate().getTime());
        model.addAttribute("now", now);
        System.out.println("Now is: " + now);

        if (model.containsAttribute("flight")) {
            Flight flight = (Flight) model.asMap().get("flight");
            airplaneList.add(flight.getAirplane());
            String formatted = format.format(flight.getFlightTime().getTime());
            model.addAttribute("flightTime", formatted);
        }

        model.addAttribute("airplanes", airplaneList);
        model.addAttribute("cities", cityService.getAllCities());
        return "admin";
    }

    @RequestMapping(value = "admin/airplanes", method = RequestMethod.GET)
    public String viewAirplanePanelPage(Model model, @RequestParam(value = "page", required = false) Integer id) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("airplanes", airplaneService.getAllAirplanes());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String now = format.format(CurrentDate.getCurrentDate().getTime());
        model.addAttribute("now", now);

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

    @Secured(value = "ROLE_ADMIN")
    @RequestMapping(value = "addflight", method = RequestMethod.POST)
    public String addFlight(Model model,
                            @RequestParam(value = "airplane") Airplane airplane,
                            @RequestParam(value = "fromcity") City cityFrom,
                            @RequestParam(value = "tocity") City cityTo,
                            @RequestParam(value = "date") String dateFromForm,
                            @RequestParam(value = "price") String price,
                            @RequestParam(value = "id", required = false) Long id) {

        Flight flight = new Flight();
        Calendar currentDate = CurrentDate.getCurrentDate();
        Calendar setDateCal = new GregorianCalendar();
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
            setDateCal.setTime(setDate);
        } catch (ParseException ea) {
            try {
                setDate = new SimpleDateFormat("yy-MM-dd").parse(dateFromForm);

                setDateCal.setTime(setDate);
            } catch (ParseException e) {
                System.out.println(e);
                model.addAttribute("error", "incorrect Date format");
                return viewAdminPanelPage(model);
            }
        }

        Calendar calendar = CurrentDate.getCurrentDate();
        calendar.setTime(setDate);

        if (cityFrom.equals(cityTo)) {
            model.addAttribute("error", "cities match");
            return viewAdminPanelPage(model);
        } else if (currentDate.compareTo(setDateCal) >= 0) {
            model.addAttribute("error", "flight date < current date");
            return viewAdminPanelPage(model);
        } else {
            //System.out.println("ID is: " + id.toString());
            if (id != null) {
                flight.setId(id);
                if (flightService.getFlightById(id).getSeats().size()>airplane.getNumOfSeats()){
                    model.addAttribute("error","Plane with ID " + airplane.getId() + " doesn't have enough seats");
                    return viewAdminPanelPage(model);
                }

            }
            flight.setFlightTime(calendar);
            flight.setFlyFromCity(cityFrom);
            flight.setFlyToCity(cityTo);
            flight.setInitPrice(fixedPrice);
            flight.setTempPrice(fixedPrice);
            //List<Long> longList = new ArrayList<>(airplane.getNumOfSeats());




            flight.setAirplane(airplane);
            if (id == null) {
                flight.setFlightCreationTime(Calendar.getInstance());
                List<Long> longList = Arrays.asList(new Long[airplane.getNumOfSeats()]);
                flight.setSeats(longList);
            } else {
                flight.setFlightCreationTime(flightService.getFlightById(id).getFlightCreationTime());
                flight.setSeats(flightService.getFlightById(id).getSeats());
            }


        }

        System.out.println("!!!!!!" + flight.getSeats().size());

        flightService.save(flight);

        model.addAttribute("msg", "flight has been added successfully");
        return viewAdminPanelPage(model);
    }


    @Secured(value = "ROLE_ADMIN")
    @RequestMapping(value = "editflight", method = RequestMethod.POST)
    public String editFlight(Model model, @RequestParam(value = "id") Flight flight) {
        flight.setInitPrice(flight.getInitPrice().subtract(flightService.getExtra(flight.getId())));
        model.addAttribute("flight", flight);
        System.out.println(flight);
        return viewAdminPanelPage(model);
    }

    @Secured(value = "ROLE_ADMIN")
    @RequestMapping(value = "deleteflight", method = RequestMethod.POST)
    public String deleteFlight(Model model, @RequestParam(value = "id") Flight flight,RedirectAttributes redirectAttributes) {
        if (flight.getSeats().size()>0){
            redirectAttributes.addFlashAttribute("error", "flight with id " + flight.getId() + " cannot be deleted");
            return "redirect:main";
        }
        flightService.delete(flight);
        redirectAttributes.addFlashAttribute("msg", "flight with id " + flight.getId() + " has been deleted");
        return "redirect:main";
    }


}
