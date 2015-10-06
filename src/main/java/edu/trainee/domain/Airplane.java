package edu.trainee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dennis on 9/15/2015.
 */

@Entity
@Table(name = "airplanes")
@NamedQueries({
        @NamedQuery(name = "Airplane.getAll", query = "select a from Airplane a"),
        @NamedQuery(name = "Airplane.getNumberOfAirplanes", query = "select count (a.id) from Airplane a "),

        //@NamedQuery(name = "Airplane.ResultPerPage", query = "select a from Airplane a limit ")
        //Other named query
})
@NamedNativeQuery(name = "Airplane.getFreeAirplanes", query = "select a.airplane_id,a.numOfSeats,a.vendorName,a.operable " +
        "from airplanes a " +
        "left join flights f on a.airplane_id=f.airplane_id " +
        "where f.airplane_id is null or f.flight_time<:curr_datetime and a.operable is TRUE ",resultClass = Airplane.class)
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airplane_id")
    private Long id;
    @Column(nullable = false)
    private Integer numOfSeats;
    @Column(nullable = false)
    private String vendorName;
    @Column(nullable = false)
    private Boolean operable;

    public Airplane() {
    }


    public Airplane(Integer numOfSeats, String vendorName, Boolean operable) {
        this.numOfSeats = numOfSeats;
        this.vendorName = vendorName;
        this.operable = operable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(Integer numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Boolean isOperable() {
        return operable;
    }

    public void setOperable(Boolean operable) {
        this.operable = operable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airplane airplane = (Airplane) o;

        if (!id.equals(airplane.id)) return false;
        if (!numOfSeats.equals(airplane.numOfSeats)) return false;
        if (!vendorName.equals(airplane.vendorName)) return false;
        return operable.equals(airplane.operable);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + numOfSeats.hashCode();
        result = 31 * result + vendorName.hashCode();
        result = 31 * result + operable.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", numOfSeats=" + numOfSeats +
                ", vendorName='" + vendorName + '\'' +
                ", operable=" + operable +
                '}';
    }
}
