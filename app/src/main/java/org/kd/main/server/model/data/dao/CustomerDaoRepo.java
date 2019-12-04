package org.kd.main.server.model.data.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.kd.main.common.entities.CorporateAccount;
import org.kd.main.common.entities.Account;
import org.kd.main.common.entities.IndividualAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CustomerDaoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    private Logger log = LoggerFactory.getLogger(CustomerDaoRepo.class);

    @Transactional
    public Account create(Account account) {

        getSession().saveOrUpdate(account);
        return account;
    }

    @Transactional
    public Account read(long id) {
        return readCustomer(id);
    }

    @Transactional
    public List<CorporateAccount> readAllCorporate() {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CorporateAccount> query = crBuilder.createQuery(CorporateAccount.class);
        Root<CorporateAccount> root = query.from(CorporateAccount.class);
        query.select(root);
        Query<CorporateAccount> q = session.createQuery(query);

        return q.getResultList();
    }

    @Transactional
    public List<IndividualAccount> readAllIndividual() {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<IndividualAccount> query = crBuilder.createQuery(IndividualAccount.class);
        Root<IndividualAccount> root = query.from(IndividualAccount.class);
        query.select(root);
        Query<IndividualAccount> q = session.createQuery(query);

        return q.getResultList();
    }

    @Transactional
    public void update(Account account) {
        var session = getSession();
        log.info("Logging session: " + session + " updating account " + account);
        session.update(account);
    }

    @Transactional
    public Account delete(long id) {
        var customer = readCustomer(id);
        deleteCustomer(customer);
        return customer; //TODO: reconsider this
    }

    public boolean isPersisted(Account account) {
        return entityManager.contains(account);
    }

    @Transactional
    public void detach(Account account) {
        entityManager.detach(account);
    }


    public Account readCustomer(long id) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Account> query = crBuilder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);
        query.select(root).where(crBuilder.equal(root.get("id"), id));//SELECT from Account WHERE id=id
        Query<Account> q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public Account read(String shortname) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        var query = crBuilder.createQuery(Account.class);
        var root = query.from(Account.class);
        query.select(root).where(crBuilder.equal(root.get("shortname"), shortname));//SELECT from Account WHERE id=id
        var q = session.createQuery(query);
        return q.getSingleResult();
    }

    void deleteCustomer(Account account) {
        var session = getSession();
        /*TODO: nulling customers required*/
        session.delete(account);
    }

    Session getSession() {
        /*
        Container like Spring is not resposible for maintaing the life cycle for the EntityManager created from it. But you can @Autowire EntityManagerFactory directly as the bean is already configured in the applicationContext. Hence this could be simple like this emf.createEntityManager().unwrap(Session.class)' to get Session`
         */

        Session session;
        if (entityManager == null
                || (session = entityManager.unwrap(Session.class)) == null) {

            throw new RuntimeException("No entityManager available");
        }
        return session;
    }

}
