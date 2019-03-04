package org.kd.main.server.model.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "parties")
public class Party implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String shortname;

    private Party(){
    }

    public Party(int id, String name, String shortname){
        this(name, shortname);
        this.id = id;
    }

    public Party(String name, String shortname){
        this.name = name;
        this.shortname = shortname;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    @Override
    public String toString() {
        return "Party " + getId() + " " + getShortname() + " " + getName();
    }
}
