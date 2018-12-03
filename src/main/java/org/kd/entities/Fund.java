package org.kd.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "funds")
public class Fund {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String shortName;
    private Date units;
    private Date employmentStartDate;

    /**
     * Bi-directional relationship between Counterparty and Fund.
     */
    @ManyToOne
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

    /**
     * Unidirectional relationship between Fund and Team.
     */
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Date getUnits() {
        return units;
    }

    public void setUnits(Date units) {
        this.units = units;
    }

    public Date getEmploymentStartDate() {
        return employmentStartDate;
    }

    public void setEmploymentStartDate(Date employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
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

        Fund fund = (Fund) o;

        return id == fund.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
