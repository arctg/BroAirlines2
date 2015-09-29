package edu.trainee.services;

import edu.trainee.domain.Order;

import java.util.Calendar;
import java.util.List;

/**
 * Created by dennis on 9/28/2015.
 */
public interface OrderService {
    public Long save(Order order);
    public List<Order> getNearest(Calendar currentTime,Long userId);
    public List<Order> getPast(Calendar currentTime,Long userId);
}
