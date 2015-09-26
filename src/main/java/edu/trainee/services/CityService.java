package edu.trainee.services;

import edu.trainee.domain.City;

import java.util.List;

/**
 * Created by dennis on 9/22/2015.
 */
public interface CityService {
    public Boolean isExisting(String name);
    public List<Long> save(City city);
    public List<City> getAllCities();
    public City getCityById(Long id);
}
