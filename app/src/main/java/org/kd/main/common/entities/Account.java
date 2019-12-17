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
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(insertable = false, updatable = false)
    private Boolean corporate;
    private String shortname;
    private String name;
    private Double balance;

    @Column(name = "bankId")
    private Long bankId;

    public Account(Boolean corporate, String shortname, String name, Double units, Long bankId) {
        this.corporate = corporate;
        this.shortname = shortname;
        this.name = name;
        this.balance = units;
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return this.shortname;
    }
}
