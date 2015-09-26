package edu.trainee.repository.implementation;

import edu.trainee.domain.City;
import edu.trainee.repository.CityRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by dennis on 9/22/2015.
 */
@Repository("cityRepository")
public class JPACityRepository implements CityRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    public City getCityById(Long id) {
        return em.find(City.class,id);
    }

    @Override
    public List<City> getAllCities() {
        TypedQuery<City> query = em.createNamedQuery("City.getAll",City.class);
        return query.getResultList();
    }

    @Override
    public Long save(City city) {
        em.persist(city);
        return city.getId();
    }

    @Override
    public void update(City city) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Boolean isExisting(String name) {
        TypedQuery<String> typedQuery = em.createNamedQuery("City.getName",String.class).setParameter("name", name);
        System.out.println();
        if (typedQuery.getResultList().size()>0){
            return true;
        }
        return false;
    }

    @Override
    public List<City> getCitiesByName(String name) {
        TypedQuery<City> typedQuery = em.createNamedQuery("City.getCityByName",City.class).setParameter("name", name);
        return typedQuery.getResultList();
    }
}
