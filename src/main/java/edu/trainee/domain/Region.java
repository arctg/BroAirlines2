package edu.trainee.domain;

import javax.persistence.*;

/**
 * Created by dennis on 9/15/2015.
 */

@Entity
@Table(name = "regions")
@NamedQueries({
        @NamedQuery(name = "Region.getAll", query = "select r from Region r"),
        @NamedQuery(name = "Region.getName", query = "select r.name from Region r where r.name=:name"),
        @NamedQuery(name = "Region.getRegionByName", query = "select r from Region r where r.name=:name"),
        //Other named query
})
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;
    @Column(name = "region_name",nullable = false)
    private String name;

    public Region() {
    }

    public Region(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (!id.equals(region.id)) return false;
        return name.equals(region.name);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

