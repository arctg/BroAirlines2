package edu.trainee.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dennis on 9/21/2015.
 */
@ControllerAdvice
public class GlobalErrorHandler {

    //@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(
            Exception exception,
            HttpServletRequest req) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("ex", exception);
        model.addObject("st",exception.getMessage());
        model.addObject("url", req.getRequestURL());
        return model;
    }
}
