package edu.trainee.repository.implementation;

import edu.trainee.domain.Airplane;
import edu.trainee.repository.AirplaneRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dennis on 9/24/2015.
 */
@Repository("airplaneRepository")
public class JPAAirplaneRepository implements AirplaneRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    public Airplane getAirplaneById(Long id) {
        return em.find(Airplane.class,id);
    }

    @Override
    public List<Airplane> getAllAirplanes() {
        TypedQuery<Airplane> query = em.createNamedQuery("Airplane.getAll",Airplane.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Long save(Airplane airplane) {
        if (airplane.getId() != null) {
            em.merge(airplane);
        } else {
            em.persist(airplane);
        }
        return airplane.getId();
    }

    @Override
    public void update(Airplane airplane) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Long getNumberOfAirplanes(){
        TypedQuery<Long> query = em.createNamedQuery("Airplane.getNumberOfAirplanes",Long.class);
        return query.getSingleResult();
    }

    @Override
    public List<Airplane> getResultPerPage(int pageNumber, int pageSize) {
        Query query = em.createNamedQuery("Airplane.getAll",Airplane.class);
        query.setFirstResult((pageNumber-1)*pageSize);
        query.setMaxResults(pageSize);
        List<Airplane> airplaneList = query.getResultList();
        return airplaneList;
    }

    @Override
    public List<Airplane> getFreeAirplanes(Calendar currentDateTime) {
        List<Airplane> typedQuery = em
                .createNamedQuery("Airplane.getFreeAirplanes",Airplane.class)
                .setParameter("curr_datetime",currentDateTime)
                .getResultList();
        return typedQuery;
    }
}
