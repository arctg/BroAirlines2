package edu.trainee.services.implementation;

import edu.trainee.logic.CurrentDate;
import edu.trainee.services.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by dennis on 9/29/2015.
 */
@ContextConfiguration(locations = {"classpath:appContext.xml", "classpath:repositoryContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceImplTest {

    @Autowired
    OrderService orderService;


    @Test
    public void testGetNearest() throws Exception {

        System.out.println(orderService.getNearest(CurrentDate.getCurrentDate(),1L));

    }
}