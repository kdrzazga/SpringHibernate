package org.kd.main.common.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "funds")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String shortname;
    private String name;
    private Double units;
    private Long party_id;

    private Customer() {
    }

    public Customer(String shortname, String name, Double units, Long party_id) {
        this.shortname = shortname;
        this.name = name;
        this.units = units;
        this.party_id = party_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    public void setParty_id(Long party_id) {
        this.party_id = party_id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortname() {
        return shortname;
    }

    public Double getUnits() {
        return units;
    }

    public Long getParty_id() {
        return party_id;
    }

    @Override
    public String toString() {
        return "Customer " + getId() + " " + getShortname() + " " + getName() + " with units: " + getUnits();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Double.compare(customer.units, units) == 0 &&
                Objects.equals(party_id, customer.party_id) &&
                Objects.equals(shortname, customer.shortname) &&
                Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shortname, name, units, party_id);
    }
}
