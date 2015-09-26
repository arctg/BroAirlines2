package edu.trainee.repository;

import edu.trainee.domain.Flight;

import java.util.List;

/**
 * Created by dennis on 9/24/2015.
 */
public interface FlightRepository {
    public Flight getFlightById(Long id);
    public List<Flight> getAllFlights();
    public Long save(Flight flight);
    public void update(Flight flight);
    public void delete(Integer id);
}
