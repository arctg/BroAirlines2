package edu.trainee.services.implementation;

import edu.trainee.domain.Flight;
import edu.trainee.repository.FlightRepository;
import edu.trainee.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dennis on 9/24/2015.
 */
@Service("flightService")
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Override
    public Long save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.getAllFlights();
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.getFlightById(id);
    }
}
