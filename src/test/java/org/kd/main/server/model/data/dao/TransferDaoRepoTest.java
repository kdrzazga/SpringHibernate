package org.kd.main.server.model.data.dao;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.main.server.TraderServer;
import org.kd.main.common.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.function.Predicate;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@SpringBootTest(classes = {TraderServer.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class TransferDaoRepoTest {

    @Autowired
    private TransferDaoRepo transferDaoRepo;

    @Autowired
    private CustomerDaoRepo customerDaoRepo;

    @Test
    public void testGetTransferByPrimaryKey() {
        assertEquals(3002L, transferDaoRepo.readTransferByPrimaryKey(3002L).getId());
        assertEquals(3003L, transferDaoRepo.readTransferByPrimaryKey(3003L).getId());
        assertEquals(3005L, transferDaoRepo.readTransferByPrimaryKey(3005L).getId());
    }

    @Test
    public void testRemoveTransferByPrimaryKey() {
        var id = 3006L;
        transferDaoRepo.deleteTransferByPrimaryKey(id);
        Assert.assertNull(transferDaoRepo.readTransferByPrimaryKey(id));
        transferDaoRepo.book(2003, 2004, 30.02f);//books transact again, but id will change
    }

    @Test
    public void testGetTransfersForParticularFund() {
        var tradeForFund2002 = transferDaoRepo.readTransferByFundId(2002);
        Assert.assertNotNull(tradeForFund2002);
        assertEquals(3, tradeForFund2002.size());
    }

    @Test
    public void testGetAllTransfers() {
        var transacts = transferDaoRepo.readAllTransfers();

        Assert.assertNotNull(transacts);
        assertThat(transacts, hasSize(greaterThan(0)));
        assertEquals(6, transacts.size());
    }

    @Test
    public void testBookInternalTransfere() {
        var srcFundId = 2002L;
        var commonPartyId = customerDaoRepo.get(srcFundId).getParty_id();

        checkBookingTransfer(srcFundId, fund -> fund.getParty_id() == commonPartyId);
    }

    @Ignore("functionality not implemented yet")
    @Test
    public void testBookExternalTransfere() {
        var srcFundId = 2011L;
        var commonPartyId = customerDaoRepo.get(srcFundId).getParty_id();

        checkBookingTransfer(srcFundId, fund -> fund.getParty_id() != commonPartyId);
    }

    private void checkBookingTransfer(long srcFundId, Predicate<Customer> partyIdComparisonPredicate) {

        var destFund = customerDaoRepo.getAllCustomers()
                .stream()
                .filter(partyIdComparisonPredicate)
                .findFirst();

        if (!destFund.isPresent())
            fail("Wrong test data. Cannot book Transfer. Only one fund with appropriate party id ");

        final int errorCode = -1;
        final long newTradeId =  transferDaoRepo.book(srcFundId, destFund.get().getId(), 0.5f);

        assertNotEquals(errorCode, newTradeId);

        removeBookedTransfer(newTradeId);
    }

    private void removeBookedTransfer(long id) {
        transferDaoRepo.deleteTransferByPrimaryKey(id);
    }

}
