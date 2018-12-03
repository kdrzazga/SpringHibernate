package org.kd.dao;

import org.kd.entities.Fund;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.inject.Named;

@Named
public class FundDaoImpl extends HibernateDaoSupport implements FundDao {

    public Fund get(long id) {
        return getHibernateTemplate().get(Fund.class, id);
    }
}
