package org.kd.dao;

import org.kd.entities.Trade;

import java.util.List;

public interface TradeDao {
    Trade get(long id);

    List<Trade> getAllTrades();

    void save(Trade trade);
}
