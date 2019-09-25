package org.kd.main.server.model.data.dao;

import org.hibernate.Session;
import org.kd.main.common.entities.Customer;
import org.kd.main.common.entities.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TransferDaoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BankDaoRepo bankDaoRepo;

    @Autowired
    private CustomerDaoRepo customerDaoRepo;

    @Transactional
    public Transfer readTransferByPrimaryKey(long id) {
        return entityManager.find(Transfer.class, id);
    }

    @Transactional
    public void deleteTransferByPrimaryKey(long id) {
        var entity = entityManager.find(Transfer.class, id);
        //entityManager.getTradeion().begin();// this is handled by Spring @Transactional
        entityManager.remove(entity);
        //entityManager.getTradeion().commit();// this is handled by Spring @Transactional too
    }

    @Transactional
    public List<Transfer> readAllTransfers() {
        var session = entityManager.unwrap(Session.class);
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(Transfer.class);
        criteria.from(Transfer.class);

        var transacts = session.createQuery(criteria).getResultList();
        session.close();
        return transacts;
    }

    @Transactional
    public List<Transfer> readTransferByFundId(long fundId) {
        var session = entityManager.unwrap(Session.class);
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(Transfer.class);

        var root = criteria.from(Transfer.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("dest_fund_id"), fundId));

        var transacts = session.createQuery(criteria).getResultList();
        session.close();
        return transacts;
    }

    @Transactional
    public long book(long sourceFundIs, long destFundId, float units) {

        var destFund = customerDaoRepo.get(destFundId);
        var sourceFund = customerDaoRepo.get(sourceFundIs);

        if (destFund == null || sourceFund == null) return -1;

        return (destFund.getParty_id() == sourceFund.getParty_id())
                ? bookInternalTransfer(sourceFund, destFund, units)
                : bookExternalTransfer();
    }

    private long bookInternalTransfer(Customer sourceCustomer, Customer destCustomer, float units) {
        if (sourceCustomer.getUnits() < units) return -1;

        sourceCustomer.setUnits(sourceCustomer.getUnits() - units);
        destCustomer.setUnits(destCustomer.getUnits() + units);

        customerDaoRepo.update(sourceCustomer);
        customerDaoRepo.update(destCustomer);
        return createTransfer(sourceCustomer.getId(), destCustomer.getId(), units, true);
    }

    private long bookExternalTransfer() {
        throw new RuntimeException("Not implemented yet");
        //TODO implement
    }

    private long createTransfer(long sourceFundId, long destFundId, float units, boolean internal){
        var newTrade = new Transfer(sourceFundId, destFundId, units, internal);

        entityManager.persist(newTrade);
        entityManager.flush();

        return newTrade.getId();
    }

}
