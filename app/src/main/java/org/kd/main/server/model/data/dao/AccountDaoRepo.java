package org.kd.main.server.model.data.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.kd.main.common.entities.Account;
import org.kd.main.common.entities.CorporateAccount;
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
import java.util.Optional;

@Repository
public class AccountDaoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    private final Logger log = LoggerFactory.getLogger(AccountDaoRepo.class);

    @Transactional
    public Account create(Account account) {

        getSession().saveOrUpdate(account);
        return account;
    }

    @Transactional
    public Optional<Account> read(long id) {
        return readAccount(id);
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
    public Optional<Account> delete(long id) {
        var account = readAccount(id);

        if (account.isPresent())
            deleteAccount(account.get());
        else
            log.error("Attempt to delete non-existing account");

        return account; //TODO: reconsider this
    }

    public boolean isPersisted(Account account) {
        return entityManager.contains(account);
    }

    @Transactional
    public void detach(Account account) {
        entityManager.detach(account);
    }


    private Optional<Account> readAccount(long id) {
        var account = this.entityManager.find(Account.class, id);
        return Optional.of(account);
    }

    @Transactional
    public List<Account> readAccountsOfBank(long bankId) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Account> query = crBuilder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);
        query.select(root).where(crBuilder.equal(root.get("bankId"), bankId));//SELECT from Account WHERE bankId=bankId
        Query<Account> q = session.createQuery(query);
        return q.getResultList();
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

    void deleteAccount(Account account) {
        var session = getSession();
        /*TODO: nulling account in bank and transfer required*/

        session.delete(account);
    }

    Session getSession() {
        Session session;
        if (entityManager == null
                || (session = entityManager.unwrap(Session.class)) == null) {

            throw new RuntimeException("No entityManager available");
        }
        return session;
    }

}
