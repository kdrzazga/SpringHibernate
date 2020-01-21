package org.kd.main.server.model.data.dao;

import org.hibernate.Session;
import org.kd.main.common.entities.Account;
import org.kd.main.common.entities.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BankDaoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    private final AccountDaoRepo accountDaoRepo;

    @Autowired
    public BankDaoRepo(AccountDaoRepo accountDaoRepo) {
        this.accountDaoRepo = accountDaoRepo;
    }

    @Transactional
    public long create(Bank bank) {
        getSession().saveOrUpdate(bank);
        return bank.getId();
    }

    @Transactional
    public List<Bank> readAll() {
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(Bank.class);
        criteria.from(Bank.class);

        return session.createQuery(criteria).getResultList();
    }

    @Transactional
    public Bank read(long id) {
        return readBank(id);
    }

    private Bank readBank(long id) {
        return entityManager.find(Bank.class, id);
    }

    @Transactional
    public Bank read(String shortname) {
        var session = getSession();
        var crBuilder = session.getCriteriaBuilder();
        var query = crBuilder.createQuery(Bank.class);
        var root = query.from(Bank.class);
        query.select(root).where(crBuilder.equal(root.get("shortname"), shortname));//SELECT from Bank WHERE id=id
        var q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional
    public List<Account> readAssociatedAccounts(long bankId) {
        return getAssociatedAccounts(bankId);
    }

    private List<Account> getAssociatedAccounts(long bankId) {
        return accountDaoRepo.readAccountsOfBank(bankId);
    }

    @Transactional
    public void update(Bank bank) {
        var session = getSession();
        session.update(bank);
    }

    @Transactional
    public boolean deleteWithFkNulling(long id){
        var bank = readBank(id);
        getAssociatedAccounts(id).forEach(account -> {
            account.setBankId(null);
            accountDaoRepo.update(account);
        });

        getSession().delete(bank);
        return true;
    }

    @Transactional
    public boolean deleteWithRelatedAccounts(long id){
        var bank = readBank(id);
        getAssociatedAccounts(id).forEach(accountDaoRepo::deleteAccount);

        getSession().delete(bank);
        return true;
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
