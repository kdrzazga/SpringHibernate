package org.kd.main.server.model.data.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.kd.main.common.entities.Log;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class LogDaoRepo {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public long create(Log city) {
        getSession().save(city);
        return city.getId();
    }

    @Transactional
    public List<Log> readAll() {
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(Log.class);
        criteria.from(Log.class);

        return session.createQuery(criteria).getResultList();
    }

    @Transactional
    public Log read(long id) {
        return readLog(id);
    }

    public Log readLog(long id) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Log> query = crBuilder.createQuery(Log.class);
        Root<Log> root = query.from(Log.class);
        query.select(root).where(crBuilder.equal(root.get("id"), id));//SELECT from Customer WHERE id=id
        Query<Log> q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public Log read(String shortname) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        var query = crBuilder.createQuery(Log.class);
        var root = query.from(Log.class);
        query.select(root).where(crBuilder.equal(root.get("shortname"), shortname));//SELECT from Log WHERE id=id
        var q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public void update(Log bank) {
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
