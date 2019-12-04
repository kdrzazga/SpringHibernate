package org.kd.main.server.model.data.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.kd.main.common.entities.City;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CityDaoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public long create(City city) {
        getSession().save(city);
        return city.getId();
    }

    @Transactional
    public List<City> readAll() {
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(City.class);
        criteria.from(City.class);

        return session.createQuery(criteria).getResultList();
    }

    @Transactional
    public City read(long id) {
        return readCity(id);
    }

    public City readCity(long id) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<City> query = crBuilder.createQuery(City.class);
        Root<City> root = query.from(City.class);
        query.select(root).where(crBuilder.equal(root.get("id"), id));//SELECT from Account WHERE id=id
        Query<City> q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public City read(String shortname) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        var query = crBuilder.createQuery(City.class);
        var root = query.from(City.class);
        query.select(root).where(crBuilder.equal(root.get("shortname"), shortname));//SELECT from City WHERE id=id
        var q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public void update(City bank) {
        var session = getSession();
        session.update(bank);
    }

    protected Session getSession() {
        Session session;
        if (entityManager == null
                || (session = entityManager.unwrap(Session.class)) == null) {

            throw new NullPointerException();
        }
        return session;
    }

}
