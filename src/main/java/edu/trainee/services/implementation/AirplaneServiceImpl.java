package edu.trainee.services.implementation;

import edu.trainee.domain.Airplane;
import edu.trainee.domain.Flight;
import edu.trainee.logic.CurrentDate;
import edu.trainee.repository.AirplaneRepository;
import edu.trainee.repository.FlightRepository;
import edu.trainee.services.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dennis on 9/24/2015.
 */
@Service("airplaneService")
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    AirplaneRepository airplaneRepository;

    @Autowired
    FlightRepository flightRepository;

    @Override
    public Long save(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    @Override
    public List<Airplane> getAllAirplanes() {
        return airplaneRepository.getAllAirplanes();
    }

    @Override
    public Airplane getAirplaneById(Long id) {
        return airplaneRepository.getAirplaneById(id);
    }

    @Override
    @Deprecated
    public List<Airplane> getFreeAirplanes() {

        if (flightRepository.getAllFlights().isEmpty()) {
            if (getAllAirplanes().isEmpty()) return null;
            else return getAllAirplanes();
        }

        List<Airplane> freeAirplanes = getAllAirplanes();

        for (Airplane airplane : getAllAirplanes()) {
            for (Flight flight : flightRepository.getAllFlights()) {
                if (airplane.isOperable()==false || (flight.getAirplane().equals(airplane) && CurrentDate.getCurrentDate().compareTo(flight.getFlightTime()) <= 0 )) {
                    freeAirplanes.remove(airplane);
                    System.out.println(airplane);
                }
            }
        }
        return freeAirplanes;
    }

    @Override
    public Long getNumberOfAirplanes() {
        return airplaneRepository.getNumberOfAirplanes();
    }

    @Override
    public List<Airplane> getResultPerPage(int pageNumber,int pageSize) {
        if (pageSize>airplaneRepository.getNumberOfAirplanes()) return airplaneRepository.getAllAirplanes();
        return airplaneRepository.getResultPerPage(pageNumber,pageSize);
    }

    @Override
    public List<Airplane> getFreeAirplaness(Calendar currentDateTime) {
        return airplaneRepository.getFreeAirplanes(currentDateTime);
    }
}
