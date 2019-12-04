package org.kd.main.common.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Country implements Serializable {

    @Id
    private Long id;

    private String name;
    private String shortname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    public Country(String name, String shortname, Currency currency) {
        this.name = name;
        this.shortname = shortname;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Country " + id + " " + shortname + " " + name;
    }
}
