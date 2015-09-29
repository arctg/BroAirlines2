package edu.trainee.repository.implementation;

import edu.trainee.domain.User;
import edu.trainee.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by dennis on 9/15/2015.
 */
@Repository("userRepository")
public class JPAUserRepository implements UserRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    public User getUserById(Long id) {
        return em.find(User.class,id);
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createNamedQuery("User.getAll",User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Long save(User user) {
        em.persist(user);
        return user.getId();
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Boolean isExisting(String email) {
        TypedQuery<String> typedQuery = em.createNamedQuery("User.getEmail",String.class).setParameter("email", email);
        System.out.println();
        if (typedQuery.getResultList().size()>0){
            return true;
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        TypedQuery<User> typedQuery = em.createNamedQuery("User.getUserByEmail",User.class).setParameter("email",email);
        return typedQuery.getSingleResult();
    }
}
