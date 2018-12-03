package org.kd.dao;

import org.kd.entities.Fund;
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
        Fund fund = fundDao.get(1);
        assertNotNull(fund);
        assertEquals("Bob", fund.getName());

        assertNotNull("Counterparty not found for fund", fund.getCounterparty());

        assertNotNull("Team not set for fund", fund.getTeam());
        assertEquals("Super team", fund.getTeam().getName());
    }

    @Test
    public void testGetNoCompanyOrTeam() {
        Fund fund = fundDao.get(2);
        assertNotNull(fund);
        assertNull(fund.getCounterparty());
        assertNull(fund.getTeam());
    }
}
