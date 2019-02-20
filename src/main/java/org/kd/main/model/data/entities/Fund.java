package org.kd.main.model.data.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FUNDS")
public class Fund implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String shortName;
    private Double units;

    /**
     * Bi-directional relationship between Party and Fund.
     */
    @ManyToOne
    @JoinColumn(name = "PARTY_ID")
    private Party party;

    /**
     * Unidirectional relationship between Fund and Trade.
     */
    @ManyToOne
    @JoinColumn(name = "TRADE_ID")
    private Trade trade;

    public Fund(){}

    public Fund(long id, String newName, String newShortName, double units) {
        this.id = id;
        this.name = newName;
        this.shortName = newShortName;
        this.units = units;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        var fund = (Fund) o;

        return id == fund.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
