package org.kd.dao;

import org.kd.entities.Fund;

import java.util.List;

public interface FundDao {
    Fund get(long id);

    List<Fund> getAvailableFunds();

    void save(Fund fund);
}
