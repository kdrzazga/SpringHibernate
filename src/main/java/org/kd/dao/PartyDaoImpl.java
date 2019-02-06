package org.kd.dao;

import org.kd.entities.Party;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.inject.Named;
import java.util.List;

@Named
public class PartyDaoImpl extends HibernateDaoSupport implements PartyDao {

    public Party get(long id) {
        return getHibernateTemplate().get(Party.class, id);
    }

    public List<Party> getAvailableCptiesIds(){
        return getHibernateTemplate().loadAll(Party.class);
    }

    @Override
    public void save(Party party) {
        getHibernateTemplate().saveOrUpdate(party);
    }
}
