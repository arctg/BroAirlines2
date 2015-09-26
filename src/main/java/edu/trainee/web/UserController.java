package edu.trainee.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dennis on 9/23/2015.
 */
@Controller
public class UserController extends AbstractController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String viewMainPage(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("cities",cityService.getAllCities());
        return "main";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewRootPage(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("cities",cityService.getAllCities());
        return "redirect:main";
    }
}
