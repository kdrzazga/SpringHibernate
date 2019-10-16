package org.kd.main.common.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Country implements Serializable {

    @Id
    //@GeneratedValue
    private Long id;

    private String name;
    private String shortname;

    public Country(String name, String shortname) {
        this.name = name;
        this.shortname = shortname;
    }

    @Override
    public String toString() {
        return "Country " + id + " " + shortname + " " + name;
    }
}
