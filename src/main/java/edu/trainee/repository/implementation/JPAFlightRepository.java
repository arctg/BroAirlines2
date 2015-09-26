package edu.trainee.repository.implementation;

import edu.trainee.domain.Flight;
import edu.trainee.repository.FlightRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by dennis on 9/24/2015.
 */
@Repository("flightRepository")
public class JPAFlightRepository implements FlightRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    public Flight getFlightById(Long id) {
       return em.find(Flight.class,id);
    }

    @Override
    public List<Flight> getAllFlights() {
        TypedQuery<Flight> query = em.createNamedQuery("Flight.getAll",Flight.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Long save(Flight flight) {
        if (flight.getId() != null) {
            em.merge(flight);
        } else {
            em.persist(flight);
        }
        return flight.getId();
    }

    @Override
    public void update(Flight flight) {

    }

    @Override
    public void delete(Integer id) {

    }
}
