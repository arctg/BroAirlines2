package edu.trainee.services;

import edu.trainee.domain.Region;

/**
 * Created by dennis on 9/22/2015.
 */
public interface RegionService {
    public Boolean isExisting(String name);
    public Long save(Region region);
    public Region getRegionByName(String name);
}
