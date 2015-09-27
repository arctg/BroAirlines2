package edu.trainee.web;

import edu.trainee.domain.City;
import edu.trainee.logic.CurrentDate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by dennis on 9/23/2015.
 */
@Controller
public class UserController extends AbstractController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String viewMainPage(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("flights", flightService.get10Nearest(CurrentDate.getCurrentDate()));
        return "main";
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
                              @RequestParam(value = "begindate",required = true) String beginDateString,
                              @RequestParam(value = "enddate",required = true) String endDateString
                              ) {

        Date beginDate = null;
        Date endDate = null;
        Calendar beginDateCal = new GregorianCalendar();
        Calendar endDateCal = new GregorianCalendar();

        if (fromCity.equals(toCity)){
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

        if (CurrentDate.getCurrentDate().compareTo(endDateCal)>0){
            model.addAttribute("error", "Time range you entered is incorrect");
            return viewMainPage(model);
        }

        if (CurrentDate.getCurrentDate().compareTo(beginDateCal)>0){
            beginDateCal = CurrentDate.getCurrentDate();
        }




        model.addAttribute("result",flightService.find(fromCity,toCity,beginDateCal,endDateCal));
        model.addAttribute("daterangefrom",beginDateString);
        model.addAttribute("daterangeto",endDateString);

        return viewMainPage(model);
    }

}
