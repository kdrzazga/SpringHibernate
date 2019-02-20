package org.kd.main.model.data.dao;

import org.kd.main.model.data.entities.Fund;

import java.util.List;

public interface FundDao {
    Fund get(long id);

    List<Fund> getAvailableFunds();

    void save(Fund fund);
}
