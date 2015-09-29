package edu.trainee.services.implementation;

import edu.trainee.services.FlightService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by dennis on 9/28/2015.
 */
@ContextConfiguration(locations = {"classpath:appContext.xml", "classpath:repositoryContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightServiceImplTest {

    @Autowired
    FlightService flightService;

    @Test
    public void testGetExtra() throws Exception {

        BigDecimal extra = flightService.getExtra(11L);
        BigDecimal expectedSize = BigDecimal.valueOf(5.74);
        assertEquals(expectedSize, extra);

    }
}