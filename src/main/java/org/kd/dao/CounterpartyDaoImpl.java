package org.kd.dao;

import org.kd.entities.Counterparty;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.inject.Named;

@Named
public class CounterpartyDaoImpl extends HibernateDaoSupport implements CounterpartyDao {

    public Counterparty get(long id) {
        return getHibernateTemplate().get(Counterparty.class, id);
    }
}
