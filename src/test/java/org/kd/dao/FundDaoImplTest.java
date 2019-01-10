package org.kd.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FundDaoImplTest extends BaseDaoTest {
    @Autowired
    private FundDao fundDao;

    @Test
    public void testGet() {
        var fund = fundDao.get(1);

        assertNotNull(fund);
        assertEquals("IBM", fund.getName());
        assertNotNull("Party not found for fund", fund.getParty());
        assertNotNull("Trade not set for fund", fund.getTrade());
        assertEquals(100, fund.getTrade().getQuantity(), 0.01);
    }

    @Test
    public void testGetNoCompanyOrTeam() {
        var fund = fundDao.get(2);

        assertNotNull(fund);
        assertNull(fund.getParty());
        assertNull(fund.getTrade());
    }
}
