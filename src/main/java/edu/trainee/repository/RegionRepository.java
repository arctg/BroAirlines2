package edu.trainee.repository;

import edu.trainee.domain.Region;

import java.util.List;

/**
 * Created by dennis on 9/22/2015.
 */
public interface RegionRepository {
    public Region getRegionById(Long id);
    public List<Region> getAllRegion();
    public Long save(Region user);
    public void update(Region user);
    public void delete(Integer id);
    public Boolean isExisting(String name);
    public Region getRegionByName(String name);
}
