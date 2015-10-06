package edu.trainee.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by dennis on 9/15/2015.
 */

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "Order.getAll", query = "select o from Order o"),
        @NamedQuery(name = "Order.getNearest", query = "select o from Order o " +
                "where o.flight.flightTime>:currentTime and o.user.id=:id " +
                "order by o.flight.flightTime"),
        @NamedQuery(name = "Order.getPast",query = "select o from Order o " +
                "where o.flight.flightTime<:currentTime and o.user.id=:id " +
                "order by o.orderCreationTime")
        //Other named query
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "flight_id",nullable = false)
    private Flight flight;
    @Column(nullable = false)
    private Boolean luggage;
    @Column(nullable = false)
    private Boolean priorityBoarding;
    @Column(nullable = false)
    private BigDecimal totalPrice;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    private Long seat;
    @Column(name = "order_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar orderCreationTime;

    public Order() {
    }

    public Order(Flight flight,
                 User user,
                 Boolean luggage,
                 Boolean priorityBoarding,
                 BigDecimal totalPrice,
                 Long seat,
                 Calendar orderCreationTime) {
        this.flight = flight;
        this.luggage = luggage;
        this.priorityBoarding = priorityBoarding;
        this.totalPrice = totalPrice;
        this.user = user;
        this.seat = seat;
        this.orderCreationTime = orderCreationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Boolean getLuggage() {
        return luggage;
    }

    public void setLuggage(Boolean luggage) {
        this.luggage = luggage;
    }

    public Boolean getPriorityBoarding() {
        return priorityBoarding;
    }

    public void setPriorityBoarding(Boolean priorityBoarding) {
        this.priorityBoarding = priorityBoarding;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getSeat() {
        return seat;
    }

    public void setSeat(Long seat) {
        this.seat = seat;
    }

    public Calendar getOrderCreationTime() {
        return orderCreationTime;
    }

    public void setOrderCreationTime(Calendar orderCreationTime) {
        this.orderCreationTime = orderCreationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!id.equals(order.id)) return false;
        if (!flight.equals(order.flight)) return false;
        if (!luggage.equals(order.luggage)) return false;
        if (!priorityBoarding.equals(order.priorityBoarding)) return false;
        if (!totalPrice.equals(order.totalPrice)) return false;
        return user.equals(order.user);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + flight.hashCode();
        result = 31 * result + luggage.hashCode();
        result = 31 * result + priorityBoarding.hashCode();
        result = 31 * result + totalPrice.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", flight=" + flight +
                ", luggage=" + luggage +
                ", priorityBoarding=" + priorityBoarding +
                ", totalPrice=" + totalPrice +
                ", user=" + user +
                ", seat=" + seat +
                ", orderCreationTime=" + orderCreationTime.getTime() +
                '}';
    }
}
