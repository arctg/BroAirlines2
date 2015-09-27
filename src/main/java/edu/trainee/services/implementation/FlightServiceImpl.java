package edu.trainee.services.implementation;

import edu.trainee.domain.City;
import edu.trainee.domain.Flight;
import edu.trainee.logic.CurrentDate;
import edu.trainee.logic.PriceFixer;
import edu.trainee.repository.FlightRepository;
import edu.trainee.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
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

    @Override
    public List<Flight> get10Nearest(Calendar calendar) {
        List<Flight> flightList = flightRepository.get10Nearest(calendar);
        for (Flight flight : flightList) {
            flight.setInitPrice(flight.getInitPrice().add(PriceFixer.fixer(flight.getFlightCreationTime(),
                    flight.getFlightTime(),
                    flight.getInitPrice(),
                    flight.getAirplane().getNumOfSeats(),
                    flight.getSeats().size())));
        }
        return flightList;
    }

    @Override
    public List<Flight> find(City fromCity, City toCity, Calendar beginDate, Calendar endDate) {
        return flightRepository.find(fromCity,toCity,beginDate,endDate);
    }
}
