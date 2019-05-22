package org.kd.main.common.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TRADES")
public class Transfer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int src_fund_id;
    private int dest_fund_id;
    private float units;
    private boolean internal;

    private Transfer() {
    }

    public Transfer(int src_fund_id, int dest_fund_id, float units, boolean internal) {
        this.src_fund_id = src_fund_id;
        this.dest_fund_id = dest_fund_id;
        this.units = units;
        this.internal = internal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getUnits() {
        return units;
    }

    public void setUnits(float units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Transaction " + this.id + "from Customer " + getDest_fund_id() + " to party " + getSrc_fund_id();
    }

    public int getDest_fund_id() {
        return dest_fund_id;
    }

    public void setDest_fund_id(int dest_fund_id) {
        this.dest_fund_id = dest_fund_id;
    }

    public int getSrc_fund_id() {
        return src_fund_id;
    }

    public void setSrc_fund_id(int src_fund_id) {
        this.src_fund_id = src_fund_id;
    }

    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transfer)) return false;
        var transfer = (Transfer) o;
        return id == transfer.id &&
                src_fund_id == transfer.src_fund_id &&
                dest_fund_id == transfer.dest_fund_id &&
                Float.compare(transfer.units, units) == 0 &&
                internal == transfer.internal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, src_fund_id, dest_fund_id, units, internal);
    }
}