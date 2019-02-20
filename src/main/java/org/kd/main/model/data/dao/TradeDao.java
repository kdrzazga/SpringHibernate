package org.kd.main.model.data.dao;

import org.kd.main.model.data.entities.Trade;

import java.util.List;

public interface TradeDao {
    Trade get(long id);

    List<Trade> getAllTrades();

    void save(Trade trade);
}
