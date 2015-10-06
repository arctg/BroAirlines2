package edu.trainee.services.implementation;

import edu.trainee.domain.Airplane;
import edu.trainee.services.AirplaneService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dennis on 10/5/2015.
 */

@ContextConfiguration(locations = {"classpath:appContext.xml", "classpath:repositoryContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AirplaneServiceImplTest {

    @Autowired
    AirplaneService airplaneService;

    @Test
    public void testGetFreeAirplaness() throws Exception {

        Calendar calendar = Calendar.getInstance();
        List<Airplane> airplaneList = airplaneService.getFreeAirplaness(calendar);

        for(Airplane airplane:airplaneList){
            System.out.println(airplane);
        }
    }
}