package org.kd.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "parties")
public class Party implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String shortName;

    /*
        @OneToMany(mappedBy = "counterparty")
        private Set<Fund> funds;
    */
    public long getId() {

        return id;
    }

    public void setId(long id) {
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
        if (o == null
                || getClass() != o.getClass()
                || !o.getClass().equals(Party.class))
            return false;

        var party = (Party) o;

        return id == party.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
