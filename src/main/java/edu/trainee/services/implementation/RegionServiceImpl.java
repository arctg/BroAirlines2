package edu.trainee.services.implementation;

import edu.trainee.domain.Region;
import edu.trainee.repository.RegionRepository;
import edu.trainee.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dennis on 9/22/2015.
 */
@Service("regionService")
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionRepository regionRepository;

    @Override
    public Boolean isExisting(String name) {
        return regionRepository.isExisting(name);
    }

    @Override
    @Transactional
    public Long save(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public Region getRegionByName(String name) {
        return regionRepository.getRegionByName(name);
    }
}
