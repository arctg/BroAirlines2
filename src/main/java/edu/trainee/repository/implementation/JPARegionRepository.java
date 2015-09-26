package edu.trainee.repository.implementation;

import edu.trainee.domain.Region;
import edu.trainee.repository.RegionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by dennis on 9/22/2015.
 */
@Repository("regionRepository")
public class JPARegionRepository implements RegionRepository {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    public Region getRegionById(Long id) {
        return em.find(Region.class,id);
    }

    @Override
    public List<Region> getAllRegion() {
        TypedQuery<Region> query = em.createNamedQuery("Region.getAll",Region.class);
        return query.getResultList();
    }

    @Override
    public Long save(Region region) {
        em.persist(region);
        return region.getId();
    }

    @Override
    public void update(Region user) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Boolean isExisting(String name) {
        TypedQuery<String> typedQuery = em.createNamedQuery("Region.getName",String.class).setParameter("name", name);
        System.out.println();
        if (typedQuery.getResultList().size()>0){
            return true;
        }
        return false;
    }

    @Override
    public Region getRegionByName(String name) {
        TypedQuery<Region> typedQuery = em.createNamedQuery("Region.getRegionByName",Region.class).setParameter("name", name);
        return typedQuery.getSingleResult();
    }
}
