package edu.trainee.repository;

import edu.trainee.domain.Airplane;

import java.util.List;

/**
 * Created by dennis on 9/24/2015.
 */
public interface AirplaneRepository {
    public Airplane getAirplaneById(Long id);
    public List<Airplane> getAllAirplanes();
    public Long save(Airplane airplane);
    public void update(Airplane airplane);
    public void delete(Integer id);
    public Long getNumberOfAirplanes();
    public List<Airplane> getResultPerPage(int pageNumber, int pageSize);
}
