package edu.trainee.services.implementation;

import edu.trainee.domain.City;
import edu.trainee.repository.CityRepository;
import edu.trainee.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dennis on 9/22/2015.
 */
@Service("cityService")
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;


    @Override
    public Boolean isExisting(String name) {
        return cityRepository.isExisting(name);
    }

    @Override
    @Transactional
    public List<Long> save(City city) {
        List<Long> longsList = new ArrayList<>();
        if (cityRepository.getCitiesByName(city.getName()).isEmpty()){
            longsList.add(cityRepository.save(city));
            return longsList;
        } else {
            for(City newCity : cityRepository.getCitiesByName(city.getName())){
                if (!city.equals(newCity)){
                    longsList.add(cityRepository.save(city));
                }
                return longsList;
            }
        }
        return null;
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.getAllCities();
    }

    @Override
    public City getCityById(Long id) {
        return cityRepository.getCityById(id);
    }
}
