package org.kd.main.model.data.dao;

import org.kd.main.model.data.entities.Fund;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.inject.Named;
import java.util.List;

@Named
public class FundDaoImpl extends HibernateDaoSupport implements FundDao {

    public Fund get(long id) {
        return getHibernateTemplate().get(Fund.class, id);
    }

    @Override
    public List<Fund> getAvailableFunds() {
        return getHibernateTemplate().loadAll(Fund.class);
    }

    @Override
    public void save(Fund fund) {
        getHibernateTemplate().saveOrUpdate(fund);
    }
}
