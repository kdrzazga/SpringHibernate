package org.kd.main.server.model.data.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.main.server.TraderServer;
import org.kd.main.common.entities.Fund;
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
    private FundDaoRepo fundDaoRepo;

    @Test
    public void testGetTradeByPrimaryKey() {
        assertEquals(3002, transferDaoRepo.getTradeByPrimaryKey(3002).getId());
        assertEquals(3003, transferDaoRepo.getTradeByPrimaryKey(3003).getId());
        assertEquals(3005, transferDaoRepo.getTradeByPrimaryKey(3005).getId());
    }

    @Test
    public void testRemoveTradeByPrimaryKey() {
        transferDaoRepo.removeTradeByPrimaryKey(3006);
        Assert.assertNull(transferDaoRepo.getTradeByPrimaryKey(3006));
        transferDaoRepo.book(2003, 2004, 30.02f);//books transact again, but id will change
    }

    @Test
    public void testGetTradesForParticularFund() {
        var tradeForFund2002 = transferDaoRepo.getTradeByFundId(2002);
        Assert.assertNotNull(tradeForFund2002);
        assertEquals(3, tradeForFund2002.size());
    }

    @Test
    public void testGetAllTrades() {
        var transacts = transferDaoRepo.getAllTransfers();

        Assert.assertNotNull(transacts);
        assertThat(transacts, hasSize(greaterThan(0)));
        assertEquals(6, transacts.size());
    }

    @Test
    public void testBookInternalTrade() {
        int srcFundId = 2002;
        var commonPartyId = fundDaoRepo.get(srcFundId).getParty_id();

        checkBookingTrade(srcFundId, fund -> fund.getParty_id() == commonPartyId);
    }

    @Test
    public void testBookExternalTrade() {
        int srcFundId = 2011;
        var commonPartyId = fundDaoRepo.get(srcFundId).getParty_id();

        checkBookingTrade(srcFundId, fund -> fund.getParty_id() != commonPartyId);
    }

    private void checkBookingTrade(int srcFundId, Predicate<Fund> partyIdComparisonPredicate) {

        var destFund = fundDaoRepo.getAllFunds()
                .stream()
                .filter(partyIdComparisonPredicate)
                .findFirst();

        if (!destFund.isPresent())
            fail("Wrong test data. Cannot book Transfer. Only one fund with appropriate party id ");

        final int errorCode = -1;
        final int newTradeId =  transferDaoRepo.book(srcFundId, destFund.get().getId(), 0.5f);

        assertNotEquals(errorCode, newTradeId);

        removeBookedTrade(newTradeId);
    }

    private void removeBookedTrade(int id) {
        transferDaoRepo.removeTradeByPrimaryKey(id);
    }

}
