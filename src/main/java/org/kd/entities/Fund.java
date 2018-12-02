package org.kd.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * @author: Matt Brown
 * @since: 8/3/11
 */
@Entity
@Table(name = "funds")
public class Fund {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double units;

    /**
     * Bi-directional relationship between Fund and Counterparty.
     */
    @OneToMany(mappedBy = "fund")
    private Set<Counterparty> counterparties;

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

    public Set<Counterparty> getCounterparties() {

        return counterparties;
    }

    public void setCounterparties(Set<Counterparty> counterparties) {
        this.counterparties = counterparties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fund fund = (Fund) o;

        if (id != fund.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
