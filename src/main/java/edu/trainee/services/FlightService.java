package edu.trainee.services;

import edu.trainee.domain.Flight;

import java.util.List;

/**
 * Created by dennis on 9/24/2015.
 */
public interface FlightService {
    public Long save(Flight flight);
    public List<Flight> getAllFlights();
    public Flight getFlightById(Long id);
}
