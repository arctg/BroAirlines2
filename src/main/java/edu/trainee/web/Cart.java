package edu.trainee.web;

import edu.trainee.domain.Flight;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by dennis on 9/28/2015.
 */
@Component
@Scope("session")
public class Cart {

    private Flight flight;
    private Calendar creationTime;
    private BigDecimal baggage;
    private BigDecimal priorityBoarding;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public BigDecimal getBaggage() {
        return baggage;
    }

    public void setBaggage(BigDecimal baggage) {
        this.baggage = baggage;
    }

    public BigDecimal getPriorityBoarding() {
        return priorityBoarding;
    }

    public void setPriorityBoarding(BigDecimal priorityBoarding) {
        this.priorityBoarding = priorityBoarding;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "flight=" + flight +
                ", creationTime=" + creationTime +
                ", baggage=" + baggage +
                ", priorityBoarding=" + priorityBoarding +
                '}';
    }
}
