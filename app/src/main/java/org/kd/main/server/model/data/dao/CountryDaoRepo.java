package org.kd.main.server.model.data.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.kd.main.common.entities.Country;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CountryDaoRepo {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public long create(Country city) {
        getSession().save(city);
        return city.getId();
    }

    @Transactional
    public List<Country> readAll() {
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(Country.class);
        criteria.from(Country.class);

        return session.createQuery(criteria).getResultList();
    }

    @Transactional
    public Country read(long id) {
        return readCountry(id);
    }

    private Country readCountry(long id) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Country> query = crBuilder.createQuery(Country.class);
        Root<Country> root = query.from(Country.class);
        query.select(root).where(crBuilder.equal(root.get("id"), id));//SELECT from Account WHERE id=id
        Query<Country> q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public Country read(String shortname) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        var query = crBuilder.createQuery(Country.class);
        var root = query.from(Country.class);
        query.select(root).where(crBuilder.equal(root.get("shortname"), shortname));//SELECT from Country WHERE id=id
        var q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public void update(Country bank) {
        var session = getSession();
        session.update(bank);
    }


    private Session getSession() {
        Session session;
        if (entityManager == null
                || (session = entityManager.unwrap(Session.class)) == null) {

            throw new NullPointerException();
        }
        return session;
    }

}
