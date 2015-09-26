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
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airplane_id")
    private Long id;
    @Column(nullable = false)
    private Integer numOfSeats;
    @Column(nullable = false)
    private String vendorName;

    public Airplane() {
    }


    public Airplane(Integer numOfSeats, String vendorName) {
        this.numOfSeats = numOfSeats;
        this.vendorName = vendorName;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airplane airplane = (Airplane) o;

        if (!id.equals(airplane.id)) return false;
        if (!numOfSeats.equals(airplane.numOfSeats)) return false;
        return vendorName.equals(airplane.vendorName);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + numOfSeats.hashCode();
        result = 31 * result + vendorName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", numOfSeats=" + numOfSeats +
                ", vendorName='" + vendorName + '\'' +
                '}';
    }
}
