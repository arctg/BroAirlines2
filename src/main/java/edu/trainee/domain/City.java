package edu.trainee.domain;

import javax.persistence.*;

/**
 * Created by dennis on 9/15/2015.
 */

@Entity
@Table(name = "cities")
@NamedQueries({
        @NamedQuery(name = "City.getAll", query = "select c from City c"),
        @NamedQuery(name = "City.getName", query = "select c.name from City c where c.name=:name"),
        @NamedQuery(name = "City.getCityByName", query = "select c from City c where c.name=:name"),
        //Other named query
})
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;
    @Column(name = "city_name",nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    public City() {
    }

    public City(String name, Region region) {
        this.name = name;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (!name.equals(city.name)) return false;
        return region.equals(city.region);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + region.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", region=" + region +
                '}';
    }
}
