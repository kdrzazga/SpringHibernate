package org.kd.main.server.model.data.dao;

import org.hibernate.Session;
import org.kd.main.common.entities.DebitCard;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DebitCardDaoRepo {

    @PersistenceContext
    private
    EntityManager entityManager;

    public DebitCard read(long id) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        var query = crBuilder.createQuery(DebitCard.class);
        var root = query.from(DebitCard.class);
        query.select(root).where(crBuilder.equal(root.get("id"), id));
        var q = session.createQuery(query);
        return q.getSingleResult();
    }

    public List<DebitCard> readAll() {
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(DebitCard.class);
        criteria.from(DebitCard.class);

        return session.createQuery(criteria).getResultList();
    }

    private Session getSession() {
        Session session;
        if (entityManager == null
                || (session = entityManager.unwrap(Session.class)) == null) {

            throw new RuntimeException("No entityManager available");

        }
        return session;
    }
}
