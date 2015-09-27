package edu.trainee.web;

import edu.trainee.domain.Airplane;
import edu.trainee.domain.City;
import edu.trainee.domain.Flight;
import edu.trainee.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

/**
 * Created by dennis on 9/17/2015.
 */
public abstract class AbstractController {

    @Autowired
    protected UserService userService;
    @Autowired
    protected CityService cityService;
    @Autowired
    protected RegionService regionService;
    @Autowired
    protected AirplaneService airplaneService;
    @Autowired
    protected FlightService flightService;

    @InitBinder
    protected void airplaneBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Airplane.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String id) {
                        Airplane airplane = null;
                        if (id != null && !id.trim().isEmpty()) {
                            Long ID = Long.valueOf(id);
                            airplane = airplaneService.getAirplaneById(ID);
                        }
                        setValue(airplane);
                    }
                });
    }

    @InitBinder
    protected void cityBinder(WebDataBinder binder) {
        binder.registerCustomEditor(City.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String id) {
                        City city = null;
                        if (id != null && !id.trim().isEmpty()) {
                            Long ID = Long.valueOf(id);
                            city = cityService.getCityById(ID);
                        }
                        setValue(city);
                    }
                });
    }

    @InitBinder
    protected void flightBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Flight.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String id) {
                        Flight flight = null;
                        if (id != null && !id.trim().isEmpty()) {
                            Long ID = Long.valueOf(id);
                            flight = flightService.getFlightById(ID);
                        }
                        setValue(flight);
                    }
                });
    }
}
