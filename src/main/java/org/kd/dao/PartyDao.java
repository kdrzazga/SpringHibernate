package org.kd.dao;

import org.kd.entities.Fund;
import org.kd.entities.Party;

import java.util.List;

public interface PartyDao {

    Party get(long id);

    List getAvailableCptiesIds();

    void save(Party party);
}
