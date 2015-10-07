package edu.trainee.repository.implementation;

import edu.trainee.domain.City;
import edu.trainee.domain.Flight;
import edu.trainee.repository.FlightRepository;
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
@Repository("flightRepository")
public class JPAFlightRepository implements FlightRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    public Flight getFlightById(Long id) {
        return em.find(Flight.class, id);
    }

    @Override
    public List<Flight> getAllFlights() {
        TypedQuery<Flight> query = em.createNamedQuery("Flight.getAll", Flight.class);
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
    @Transactional
    public void delete(Flight flight) {
        Flight tempFlight = em.find(Flight.class, flight.getId());
        if (tempFlight != null) em.remove(tempFlight);


    }

    @Override
    public List<Flight> get10Nearest(Calendar calendar) {
        Query query = em.createNamedQuery("Flight.getAllSortedByDate", Flight.class).setParameter("currentTime", calendar);
        query.setFirstResult(0);
        query.setMaxResults(10);
        List<Flight> flightList = query.getResultList();
        return flightList;
    }

    @Override
    @Transactional
    public List<Flight> find(City fromCity, City toCity, Calendar beginDate, Calendar endDate) {
        TypedQuery<Flight> typedQuery = em.createNamedQuery("Flight.find", Flight.class).
                setParameter("fromCity", fromCity).
                setParameter("toCity", toCity).
                setParameter("beginDate", beginDate).
                setParameter("endDate", endDate);
        return typedQuery.getResultList();
    }
}
