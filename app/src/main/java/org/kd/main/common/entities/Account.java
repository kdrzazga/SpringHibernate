package org.kd.main.common.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "corporate", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public abstract class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(insertable = false, updatable = false)
    private Boolean corporate;
    private String shortname;
    private String name;
    private Double balance;
    private Long bank_id;

    public Account(Boolean corporate, String shortname, String name, Double units, Long bank_id) {
        this.corporate = corporate;
        this.shortname = shortname;
        this.name = name;
        this.balance = units;
        this.bank_id = bank_id;
    }

      @Override
    public String toString() {
        return "Account " + this.id + " " + this.shortname + " " + this.name + " with units: " + this.balance;
    }
}
