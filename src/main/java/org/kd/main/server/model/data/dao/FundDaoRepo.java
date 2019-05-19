package org.kd.main.server.model.data.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.kd.main.common.entities.Fund;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class FundDaoRepo  {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public long insert(Fund fund) {

        entityManager.persist(fund);
        return fund.getId();
    }

    public boolean isPersisted(Fund fund) {
        return entityManager.contains(fund);
    }

    @Transactional
    public void detach(Fund fund) {
        entityManager.detach(fund);
    }

    @Transactional
    public List<Fund> getAllFunds() {
        //javax.persistence.Query query = entityManager.createQuery("SELECT id, name, shortname, units, party_id FROM Fund");//doesn't work

        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Fund> query = crBuilder.createQuery(Fund.class);
        Root<Fund> root = query.from(Fund.class);
        query.select(root);
        Query<Fund> q = session.createQuery(query);

        return q.getResultList();
    }

    @Transactional
    public Fund get(long id) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Fund> query = crBuilder.createQuery(Fund.class);
        Root<Fund> root = query.from(Fund.class);
        query.select(root).where(crBuilder.equal(root.get("id"), id));//SELECT from Fund WHERE id=id
        Query<Fund> q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public Fund get(String shortname) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        var query = crBuilder.createQuery(Fund.class);
        var root = query.from(Fund.class);
        query.select(root).where(crBuilder.equal(root.get("shortname"), shortname));//SELECT from Fund WHERE id=id
        var q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public void update(Fund fund) {
        var session = getSession();
        session.update(fund);
    }

    Session getSession() {
        Session session;
        if (entityManager == null
                || (session = entityManager.unwrap(Session.class)) == null) {

            throw new NullPointerException();
        }
        return session;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
