package org.kd.main.server.model.data.dao;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.main.server.TraderServer;
import org.kd.main.server.model.data.entities.Fund;
import org.kd.main.server.model.data.entities.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(classes = {TraderServer.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class PartyDaoRepoTest {

    @Autowired
    private PartyDaoRepo partyDaoRepo;

    @Test
    public void testGetAllParties() {
        var allParties = partyDaoRepo.getAllParties();

        Assert.assertNotNull(allParties);
        assertTrue(allParties.size() > 0);
    }

    @Test
    public void testGetSinglePartyById() {
        var party = partyDaoRepo.get(1012L);

        Assert.assertNotNull(party);
        assertEquals(1012L, party.getId());
    }

    @Test
    public void testGetSinglePartyByName() {
        var party = partyDaoRepo.get("BABA");

        Assert.assertNotNull(party);
        assertEquals("Alibaba Group Holding", party.getName());
    }

    @Test
    public void testGetAssociatedFund() {
        List<Fund> associatedFunds = partyDaoRepo.getAssociatedFunds(1012L);

        Assert.assertNotNull(associatedFunds);
        assertTrue(associatedFunds.size() > 0);
    }

    @Test
    public void testInsert(){
        var partyId = partyDaoRepo.insert(new Party("Test Party", "TEST"));
        assertEquals(partyId, partyDaoRepo.get("TEST").getId());
    }


    @Test
    public void testPartyUpdate(){
        var newPartyName = "NEW TEST NAME";
        var party = partyDaoRepo.get(1012L);
        party.setName(newPartyName);
        partyDaoRepo.update(party);

        var readParty = partyDaoRepo.get(1012L);
        assertEquals(newPartyName, readParty.getName());
    }

    @Ignore
    @Test
    public void testPersistenceAndDetach(){
        var party = new Party("Test Party 3", "TEST3");
        var partyId = partyDaoRepo.insert(party);
        partyDaoRepo.detach(party);
        assertNotEquals(partyId, partyDaoRepo.get("TEST3").getId());
    }

}
