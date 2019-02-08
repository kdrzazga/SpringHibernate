package org.kd.dao;

import org.kd.entities.Trade;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class TradeDaoImpl extends HibernateDaoSupport implements TradeDao {

    @Override
    public Trade get(long id) {
        return getHibernateTemplate().get(Trade.class, id);
    }

    @Override
    public List<Trade> getAllTrades() {
        return getHibernateTemplate().loadAll(Trade.class);
    }

    @Override
    public void save(Trade trade) {
        getHibernateTemplate().saveOrUpdate(trade);
    }
}
