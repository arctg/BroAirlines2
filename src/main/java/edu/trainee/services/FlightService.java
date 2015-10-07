package edu.trainee.services;

import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.trainee.domain.City;
import edu.trainee.domain.Flight;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dennis on 9/24/2015.
 */
public interface FlightService {
    public Long save(Flight flight);
    public void delete(Flight flight);
    public List<Flight> getAllFlights();
    public Flight getFlightById(Long id);
    public List<Flight> get10Nearest(Calendar calendar);
    public List<Flight> find(City fromCity,City toCity, Calendar beginDate, Calendar endDate);
    public BigDecimal getExtra(Long flightId);
    public Boolean hasFreeSeats(Flight flight);
}
