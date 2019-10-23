package org.kd.main.common.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String shortname;
    private String name;
    private Double units;
    private Long bank_id;

    private Customer() {
    }

    public Customer(String shortname, String name, Double units, Long bank_id) {
        this.shortname = shortname;
        this.name = name;
        this.units = units;
        this.bank_id = bank_id;
    }

      @Override
    public String toString() {
        return "Customer " + this.id + " " + this.shortname + " " + this.name + " with units: " + this.units;
    }
}
