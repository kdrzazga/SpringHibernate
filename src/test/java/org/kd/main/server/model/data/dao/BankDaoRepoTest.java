package org.kd.main.server.model.data.dao;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.main.server.TraderServer;
import org.kd.main.common.entities.Fund;
import org.kd.main.common.entities.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(classes = {TraderServer.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class BankDaoRepoTest {

    @Autowired
    private BankDaoRepo bankDaoRepo;

    @Test
    public void testGetAllParties() {
        var allParties = bankDaoRepo.getAllBanks();

        Assert.assertNotNull(allParties);
        assertTrue(allParties.size() > 0);
    }

    @Test
    public void testGetSinglePartyById() {
        var party = bankDaoRepo.get(1012L);

        Assert.assertNotNull(party);
        assertEquals(1012L, party.getId());
    }

    @Test
    public void testGetSinglePartyByName() {
        var party = bankDaoRepo.get("BABA");

        Assert.assertNotNull(party);
        assertEquals("Alibaba Group Holding", party.getName());
    }

    @Test
    public void testGetAssociatedFund() {
        List<Fund> associatedFunds = bankDaoRepo.getAssociatedCustomers(1012L);

        Assert.assertNotNull(associatedFunds);
        assertTrue(associatedFunds.size() > 0);
    }

    @Test
    public void testInsert(){
        var partyId = bankDaoRepo.insert(new Bank("Test Bank", "TEST"));
        assertEquals(partyId, bankDaoRepo.get("TEST").getId());
    }


    @Test
    public void testPartyUpdate(){
        var newPartyName = "NEW TEST NAME";
        var party = bankDaoRepo.get(1012L);
        party.setName(newPartyName);
        bankDaoRepo.update(party);

        var readParty = bankDaoRepo.get(1012L);
        assertEquals(newPartyName, readParty.getName());
    }

    @Ignore
    @Test
    public void testPersistenceAndDetach(){
        var party = new Bank("Test Bank 3", "TEST3");
        var partyId = bankDaoRepo.insert(party);
        bankDaoRepo.detach(party);
        assertNotEquals(partyId, bankDaoRepo.get("TEST3").getId());
    }

}
