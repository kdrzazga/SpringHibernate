package org.kd.dao.hibernate;

import org.kd.dao.FundDao;
import org.kd.entities.Fund;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FundDaoImpl extends HibernateDaoSupport implements FundDao {

    public Fund get(long id) {
        return getHibernateTemplate().get(Fund.class, id);
    }
}
