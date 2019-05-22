package org.kd.main.server.model.data.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.main.server.TraderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = {TraderServer.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerDaoRepoTest {

    @Autowired
    private FundDaoRepo fundDaoRepo;

    @Test
    public void testPersistenceContextInjection(){
        assertNotNull(fundDaoRepo.getEntityManager());
    }

    @Test
    public void testGetAllFunds() {
        var allFunds = fundDaoRepo.getAllCustomers();

        Assert.assertNotNull(allFunds);
        assertTrue(allFunds.size() > 0);
    }

    @Test
    public void testGetSingleFundById() {
        var fund = fundDaoRepo.get(2012L);

        Assert.assertNotNull(fund);
        assertEquals(2012L, fund.getId());
    }
/*
    @Test
    public void testGet() {
        var fund = DataModelManagerSingleton.getInstance().getFundDao().get(3016);
        var possibleFailureCause = "Please first check if DB was changed.";

        assertNotNull(possibleFailureCause, fund);
        assertEquals(possibleFailureCause, "IBM", fund.getName());
        assertEquals(100, fund.getTrade().getQuantity(), 0.01);
    }

    @Test
    public void testGetAvailableFunds(){
        var allFunds = DataModelManagerSingleton.getInstance().getFundDao().getAvailableFunds();

        assertNotNull(allFunds);
        assertTrue(allFunds.size() > 0);
    }
*/}
