package org.kd.dao.hibernate;

import org.kd.dao.FundDao;
import org.kd.entities.Fund;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author: Matt Brown
 * @since: 8/3/11
 */
public class FundDaoImplTest extends BaseDaoTest {

    @Autowired
    private FundDao fundDao;

    @Test
    public void testGet() {
        Fund fund = fundDao.get(1);
        assertNotNull(fund);
        assertEquals("AIG", fund.getName());
        assertEquals(2, fund.getCounterparties().size());
    }

    @Test
    public void testGetCompanyWithNoEmployees() {
        Fund fund = fundDao.get(2);
        assertNotNull(fund);
        assertNotNull(fund.getCounterparties());
        assertTrue(fund.getCounterparties().isEmpty());
    }
}
