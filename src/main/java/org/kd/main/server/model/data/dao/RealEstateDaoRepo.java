package org.kd.main.server.model.data.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.kd.main.common.entities.RealEstate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RealEstateDaoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public long create(RealEstate realEstate) {
        getSession().save(realEstate);
        return realEstate.getId();
    }

    @Transactional
    public List<RealEstate> readAll() {
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(RealEstate.class);
        criteria.from(RealEstate.class);

        return session.createQuery(criteria).getResultList();
    }

    @Transactional
    public RealEstate read(long id) {
        return readRealEstate(id);
    }

    public RealEstate readRealEstate(long id) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<RealEstate> query = crBuilder.createQuery(RealEstate.class);
        Root<RealEstate> root = query.from(RealEstate.class);
        query.select(root).where(crBuilder.equal(root.get("id"), id));//SELECT from Customer WHERE id=id
        Query<RealEstate> q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public RealEstate read(String shortname) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        var query = crBuilder.createQuery(RealEstate.class);
        var root = query.from(RealEstate.class);
        query.select(root).where(crBuilder.equal(root.get("shortname"), shortname));//SELECT from RealEstate WHERE id=id
        var q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public void update(RealEstate bank) {
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
