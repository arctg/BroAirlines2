package edu.trainee.services.implementation;

import edu.trainee.domain.Order;
import edu.trainee.repository.OrderRepository;
import edu.trainee.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by dennis on 9/28/2015.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Long save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getNearest(Calendar currentTime,Long userId) {
        return orderRepository.getNearest(currentTime,userId);
    }

    @Override
    public List<Order> getPast(Calendar currentTime,Long userId) {
        return orderRepository.getPast(currentTime,userId);
    }
}
