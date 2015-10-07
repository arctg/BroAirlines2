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
    public void delete(Flight flight) {
        flightRepository.delete(flight);
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.getAllFlights();
    }

    @Override
    public Flight getFlightById(Long id) {
        Flight flight = flightRepository.getFlightById(id);
        flight.setInitPrice(flight.getTempPrice().add(getExtra(flight.getId())));
        return flight;
    }

    @Override
    public List<Flight> get10Nearest(Calendar calendar) {
        List<Flight> flightList = flightRepository.get10Nearest(calendar);
        for (Flight flight : flightList) {
            flight.setInitPrice(flight.getTempPrice().add(getExtra(flight.getId())));
        }
        return flightList;
    }

    @Override
    public List<Flight> find(City fromCity, City toCity, Calendar beginDate, Calendar endDate) {
        List<Flight> flightList = flightRepository.find(fromCity, toCity, beginDate, endDate);
        for (Flight flight : flightList) {
            flight.setInitPrice(flight.getTempPrice().add(getExtra(flight.getId())));
        }
        return flightList;

    }

    @Override
    public BigDecimal getExtra(Long flightId) {
        final BigDecimal BASE_COEFICIENT = BigDecimal.valueOf(0.25);
        BigDecimal newPrice = BigDecimal.ZERO;
        BigDecimal dateCoeficient, placeCoeficient;
        Flight flight = flightRepository.getFlightById(flightId);
        int numOfBusyPlaces = 0;

        Calendar currentDate = CurrentDate.getCurrentDate();


        if (flight.getSeats() != null) {
            for (Long seat : flight.getSeats()) {
                if (seat != null) {
                    numOfBusyPlaces++;
                }
            }
        }

//        Date date = currentDate.getTime() - createDate.getTime();
        double a = PriceFixer.hoursBetween(currentDate.getTime(), flight.getFlightCreationTime().getTime());
        //System.out.println("a: " + a);
        double b = PriceFixer.hoursBetween(flight.getFlightTime().getTime(), flight.getFlightCreationTime().getTime());
        //System.out.println("b: " + b);
        double c = (a / b);
        //System.out.println("C:" + c);


        dateCoeficient = BASE_COEFICIENT.multiply(flight.getInitPrice()).multiply(BigDecimal.valueOf(c));
        //System.out.println("Date coef: " + dateCoeficient.setScale(2, BigDecimal.ROUND_DOWN));
        placeCoeficient = BASE_COEFICIENT.
                multiply(flight.getInitPrice()).
                multiply(BigDecimal.valueOf(((double) numOfBusyPlaces) / (double) flight.getAirplane().getNumOfSeats()));
        //System.out.println("Place coef: " + placeCoeficient.setScale(2, BigDecimal.ROUND_DOWN));
        BigDecimal priceCoeficient = dateCoeficient.add(placeCoeficient);

        return priceCoeficient.setScale(2, BigDecimal.ROUND_DOWN);
    }

    @Override
    public Boolean hasFreeSeats(Flight flight) {
        int numOfBusySeats = 0;

        if (flight.getSeats() != null) {
            for (Long seat : flight.getSeats()) {
                if (seat != null) {
                    numOfBusySeats++;
                }
            }
        }

        if (numOfBusySeats != flight.getAirplane().getNumOfSeats()) {
            return true;
        }
        return false;
    }
}
