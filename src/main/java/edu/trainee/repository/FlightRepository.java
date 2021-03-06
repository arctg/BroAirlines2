package edu.trainee.repository;

import edu.trainee.domain.City;
import edu.trainee.domain.Flight;

import java.util.Calendar;
import java.util.List;

/**
 * Created by dennis on 9/24/2015.
 */
public interface FlightRepository {
    public Flight getFlightById(Long id);
    public List<Flight> getAllFlights();
    public Long save(Flight flight);
    public void update(Flight flight);
    public void delete(Flight flight);
    public List<Flight> get10Nearest(Calendar calendar);
    public List<Flight> find(City fromCity,City toCity, Calendar beginDate, Calendar endDate);
}
