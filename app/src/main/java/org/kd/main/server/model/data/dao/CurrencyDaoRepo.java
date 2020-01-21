package org.kd.main.server.model.data.dao;

import org.hibernate.Session;
import org.kd.main.common.entities.Currency;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CurrencyDaoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public long create(Currency city) {
        getSession().save(city);
        return city.getId();
    }

    @Transactional
    public List<Currency> readAll() {
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(Currency.class);
        criteria.from(Currency.class);

        return session.createQuery(criteria).getResultList();
    }

    @Transactional
    public Currency read(long id) {
        return readCurrency(id);
    }

    private Currency readCurrency(long id) {
        return entityManager.find(Currency.class, id);
    }

    @Transactional
    public Currency read(String shortname) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        var query = crBuilder.createQuery(Currency.class);
        var root = query.from(Currency.class);
        query.select(root).where(crBuilder.equal(root.get("shortname"), shortname));//SELECT from Currency WHERE id=id
        var q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public void update(Currency bank) {
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
