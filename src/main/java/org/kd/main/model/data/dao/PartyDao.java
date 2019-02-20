package org.kd.main.model.data.dao;

import org.kd.main.model.data.entities.Party;

import java.util.List;

public interface PartyDao {

    Party get(long id);

    List<Party> getAvailableCptiesIds();

    void save(Party party);
}
