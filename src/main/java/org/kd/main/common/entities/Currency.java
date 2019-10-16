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
public class Currency implements Serializable {

    @Id
    //@GeneratedValue
    private Long id;

    private String name;
    private String shortname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    public Currency(String name, String shortname) {
        this.name = name;
        this.shortname = shortname;
    }

    @Override
    public String toString() {
        return "Currency " + id + " " + shortname + " " + name;
    }
}
