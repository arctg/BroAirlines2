package edu.trainee.web;

import edu.trainee.domain.Roles;
import edu.trainee.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dennis on 9/18/2015.
 */
@Controller
public class LoginController extends AbstractController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String viewTestPage(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("message", "Hello from testController!");
        model.addAttribute("helloName", name);
        System.out.println();
        return "test";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Model msgModel,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "reg", required = false) String reg
    ) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username or password!");
        }
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }

        if(reg != null){
            model.addObject("msg","Registration successful");
        }

        model.setViewName("login");
        //return "login";
        return model;

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String goToRegister() {
        return "register";
    }

    @RequestMapping(value = "reg", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute User newUser ) {

        if (userService.isExisting(newUser.getEmail())) {
            System.out.println("Hello from register controller");
            model.addAttribute("error", "User with such email exists!");
            return "register";
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<Roles> list = new ArrayList<>();
        list.add(Roles.ROLE_USER);

        newUser.setRoles(list);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setEnabled(true);
        Long Id = userService.save(newUser);

        if (Id != null) model.addAttribute("reg","Registration successful");

        return "redirect:login";
    }


}
