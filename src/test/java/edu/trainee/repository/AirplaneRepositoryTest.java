package edu.trainee.repository;

import edu.trainee.domain.Airplane;
import edu.trainee.services.AirplaneService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dennis on 9/26/2015.
 */

@ContextConfiguration(locations = {"classpath:appContext.xml", "classpath:repositoryContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AirplaneRepositoryTest {

    @Autowired
    AirplaneService airplaneService;

    @Test
    public void testGetResultPerPage() throws Exception {

        List<Airplane> airplaneList = airplaneService.getResultPerPage(1, 3);
        System.out.println(airplaneService.getResultPerPage(1, 3));
        int expectedSize = 3;
        assertEquals(expectedSize, airplaneList.size(), 0.5);


        airplaneList = airplaneService.getResultPerPage(1, 7);
        System.out.println(airplaneService.getResultPerPage(1, 7));
        expectedSize = 6;
        assertEquals(expectedSize, airplaneService.getAllAirplanes().size(), 0.5);


    }


}