package org.kd.main.server.model.data.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BankDaoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public long insert(Bank bank) {
        entityManager.persist(bank);
        return bank.getId();
    }

    public boolean isPersisted(Bank bank) {
        return entityManager.contains(bank);
    }

    public void detach(Bank bank) {
        entityManager.detach(bank);
    }

    @Transactional
    public List<Bank> getAllBanks() {
        /*var query = entityManager.createQuery("SELECT id, name, shortname FROM Bank");

        return query.getResultList();*/
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(Bank.class);
        criteria.from(Bank.class);

        return session.createQuery(criteria).getResultList();
    }

    @Transactional
    public Bank get(long id) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Bank> query = crBuilder.createQuery(Bank.class);
        Root<Bank> root = query.from(Bank.class);
        query.select(root).where(crBuilder.equal(root.get("id"), id));//SELECT from Customer WHERE id=id
        Query<Bank> q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public Bank get(String shortname) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        var query = crBuilder.createQuery(Bank.class);
        var root = query.from(Bank.class);
        query.select(root).where(crBuilder.equal(root.get("shortname"), shortname));//SELECT from Bank WHERE id=id
        var q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public List<Customer> getAssociatedCustomers(long bankId) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        var query = crBuilder.createQuery(Customer.class);
        var root = query.from(Customer.class);
        query.select(root).where(crBuilder.equal(root.get("party_id"), bankId));//SELECT from Funds WHERE party_id=bankId
        var q = session.createQuery(query);
        return q.getResultList();
    }

    @Transactional
    public void update(Bank bank) {
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
