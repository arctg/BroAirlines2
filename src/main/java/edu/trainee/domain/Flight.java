package edu.trainee.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dennis on 9/15/2015.
 */

@Entity
@Table(name = "flights")
@NamedQueries({
        @NamedQuery(name = "Flight.getAll", query = "select f from Flight f"),
        @NamedQuery(name = "Flight.getAllSortedByDate", query = "select f " +
                "from Flight f " +
                "where f.flightTime>:currentTime and f.seats.size<f.airplane.numOfSeats " +
                "order by f.flightTime"),
        @NamedQuery(name = "Flight.find", query = "select f " +
                "from Flight f " +
                "where f.flightTime " +
                "between :beginDate and :endDate " +
                "and f.flyFromCity=:fromCity and f.flyToCity=:toCity and  f.seats.size<f.airplane.numOfSeats")
        //Other named query
})
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long id;
    @Column(name = "creation_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar flightCreationTime;
    @Column(name = "flight_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar flightTime;
    @Column(nullable = false)
    private BigDecimal initPrice;
    @Column
    private BigDecimal tempPrice;
    @ManyToOne
    @JoinColumn(name = "airplane_id", nullable = false)
    private Airplane airplane;
    @ManyToOne
    @JoinColumn(name = "fly_from", nullable = false)
    private City flyFromCity;
    @ManyToOne
    @JoinColumn(name = "fly_to", nullable = false)
    private City flyToCity;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "seats")
    private List<Long> seats;

    public Flight() {
    }

    public Flight(
            Calendar flightCreationTime,
            Calendar flightTime,
            BigDecimal initPrice,
            Airplane airplane,
            City flyFromCity,
            City flyToCity,
            List<Long> seats,
            BigDecimal tempPrice) {
        this.flightCreationTime = flightCreationTime;
        this.flightTime = flightTime;
        this.initPrice = initPrice;
        this.tempPrice = tempPrice;
        this.airplane = airplane;
        this.flyFromCity = flyFromCity;
        this.flyToCity = flyToCity;
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getFlightCreationTime() {
        return flightCreationTime;
    }

    public void setFlightCreationTime(Calendar flightCreationTime) {
        this.flightCreationTime = flightCreationTime;
    }

    public Calendar getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Calendar flightTime) {
        this.flightTime = flightTime;
    }

    public BigDecimal getInitPrice() {
        return initPrice;
    }

    public void setInitPrice(BigDecimal initPrice) {
        this.initPrice = initPrice;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public City getFlyFromCity() {
        return flyFromCity;
    }

    public void setFlyFromCity(City flyFromCity) {
        this.flyFromCity = flyFromCity;
    }

    public City getFlyToCity() {
        return flyToCity;
    }

    public void setFlyToCity(City flyToCity) {
        this.flyToCity = flyToCity;
    }

    public List<Long> getSeats() {
        return seats;
    }

    public void setSeats(List<Long> seats) {
        this.seats = seats;
    }

    public BigDecimal getTempPrice() {
        return tempPrice;
    }

    public void setTempPrice(BigDecimal tempPrice) {
        this.tempPrice = tempPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

//        if (!id.equals(flight.id)) return false;
        if (!flightCreationTime.equals(flight.flightCreationTime)) return false;
        if (!flightTime.equals(flight.flightTime)) return false;
        if (!initPrice.equals(flight.initPrice)) return false;
        if (!airplane.equals(flight.airplane)) return false;
        if (!flyFromCity.equals(flight.flyFromCity)) return false;
        return flyToCity.equals(flight.flyToCity);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + flightCreationTime.hashCode();
        result = 31 * result + flightTime.hashCode();
        result = 31 * result + initPrice.hashCode();
        result = 31 * result + airplane.hashCode();
        result = 31 * result + flyFromCity.hashCode();
        result = 31 * result + flyToCity.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightCreationTime=" + flightCreationTime +
                ", flightTime=" + flightTime +
                ", initPrice=" + initPrice +
                ", tempPrice=" + tempPrice +
                ", airplane=" + airplane +
                ", flyFromCity=" + flyFromCity +
                ", flyToCity=" + flyToCity +
                ", seats=" + seats +
                '}';
    }
}
