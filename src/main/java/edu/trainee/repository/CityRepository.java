package edu.trainee.repository;

import edu.trainee.domain.City;

import java.util.List;

/**
 * Created by dennis on 9/22/2015.
 */
public interface CityRepository {
    public City getCityById(Long id);
    public List<City> getAllCities();
    public Long save(City user);
    public void update(City user);
    public void delete(Integer id);
    public Boolean isExisting(String name);
    public List<City> getCitiesByName(String name);
}
