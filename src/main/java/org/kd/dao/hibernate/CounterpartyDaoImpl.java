package org.kd.dao.hibernate;

import org.kd.dao.CounterpartyDao;
import org.kd.entities.Counterparty;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CounterpartyDaoImpl extends HibernateDaoSupport implements CounterpartyDao {

    public Counterparty get(long id) {
        return getHibernateTemplate().get(Counterparty.class, id);
    }
}
