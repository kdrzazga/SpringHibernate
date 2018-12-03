package org.kd.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "counterparties")
public class Counterparty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    /**
     * Bi-directional relationship between Counterparty and Fund.
     */
    @OneToMany(mappedBy = "counterparty")
    private Set<Fund> funds;

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

    public Set<Fund> getFunds() {

        return funds;
    }

    public void setFunds(Set<Fund> funds) {
        this.funds = funds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Counterparty counterparty = (Counterparty) o;

        return id == counterparty.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
