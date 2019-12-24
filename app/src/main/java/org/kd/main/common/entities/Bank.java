package org.kd.main.common.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Bank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String shortname;

    public Bank(String name, String shortname) {
        this.name = name;
        this.shortname = shortname;
    }

    @Override
    public String toString() {
        return name;
    }

}
