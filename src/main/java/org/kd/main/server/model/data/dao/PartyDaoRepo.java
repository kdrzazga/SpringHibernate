package org.kd.main.server.model.data.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.kd.main.server.model.data.entities.Fund;
import org.kd.main.server.model.data.entities.Party;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PartyDaoRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public long insert(Party party) {
        entityManager.persist(party);
        return party.getId();
    }

    public boolean isPersisted(Party party) {
        return entityManager.contains(party);
    }

    public void detach(Party party) {
        entityManager.detach(party);
    }

    @Transactional
    public List<Party> getAllParties() {
        /*var query = entityManager.createQuery("SELECT id, name, shortname FROM Party");

        return query.getResultList();*/
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(Party.class);
        criteria.from(Party.class);

        return session.createQuery(criteria).getResultList();
    }

    @Transactional
    public Party get(long id) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Party> query = crBuilder.createQuery(Party.class);
        Root<Party> root = query.from(Party.class);
        query.select(root).where(crBuilder.equal(root.get("id"), id));//SELECT from Fund WHERE id=id
        Query<Party> q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public Party get(String shortname) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        var query = crBuilder.createQuery(Party.class);
        var root = query.from(Party.class);
        query.select(root).where(crBuilder.equal(root.get("shortname"), shortname));//SELECT from Party WHERE id=id
        var q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public List<Fund> getAssociatedFunds(long partyId) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        var query = crBuilder.createQuery(Fund.class);
        var root = query.from(Fund.class);
        query.select(root).where(crBuilder.equal(root.get("party_id"), partyId));//SELECT from Funds WHERE party_id=partyId
        var q = session.createQuery(query);
        return q.getResultList();
    }

    @Transactional
    public void update(Party party) {
        var session = getSession();
        session.update(party);
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
