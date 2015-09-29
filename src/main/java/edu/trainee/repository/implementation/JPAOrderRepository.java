package edu.trainee.repository.implementation;

import edu.trainee.domain.Order;
import edu.trainee.repository.OrderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dennis on 9/28/2015.
 */
@Repository("orderRepository")
public class JPAOrderRepository implements OrderRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    @Transactional
    public Long save(Order order) {
        em.persist(order);
        return order.getId();
    }

    @Override
    public List<Order> getNearest(Calendar currentTime,Long userId) {
        TypedQuery<Order> typedQuery = em.createNamedQuery("Order.getNearest",Order.class).
                setParameter("currentTime",currentTime).
                setParameter("id",userId);
        return typedQuery.getResultList();

    }

    @Override
    public List<Order> getPast(Calendar currentTime, Long userId) {
        TypedQuery<Order> typedQuery = em.createNamedQuery("Order.getPast",Order.class).
                setParameter("currentTime",currentTime).
                setParameter("id",userId);
        return typedQuery.getResultList();
    }
}
