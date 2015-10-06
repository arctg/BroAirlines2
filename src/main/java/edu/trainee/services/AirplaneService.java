package edu.trainee.services;

import edu.trainee.domain.Airplane;

import java.util.Calendar;
import java.util.List;

/**
 * Created by dennis on 9/24/2015.
 */
public interface AirplaneService {
    public Long save(Airplane airplane);
    public List<Airplane> getAllAirplanes();
    public Airplane getAirplaneById(Long id);
    public List<Airplane> getFreeAirplanes();
    public Long getNumberOfAirplanes();
    public List<Airplane> getResultPerPage(int pageNumber,int pageSize);
    public List<Airplane> getFreeAirplaness(Calendar currentDateTime);
}
