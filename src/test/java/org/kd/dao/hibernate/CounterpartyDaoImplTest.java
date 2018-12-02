package org.kd.dao.hibernate;

import org.kd.dao.CounterpartyDao;
import org.kd.entities.Counterparty;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author: Matt Brown
 * @since: 8/3/11
 */
public class CounterpartyDaoImplTest extends BaseDaoTest {
    @Autowired
    private CounterpartyDao counterpartyDao;

    @Test
    public void testGet() {
        Counterparty counterparty = counterpartyDao.get(1);
        assertNotNull(counterparty);
        assertEquals("Bob", counterparty.getFirstName());

        assertNotNull("Fund not found for counterparty", counterparty.getFund());

        assertNotNull("Team not set for counterparty", counterparty.getTeam());
        assertEquals("Super team", counterparty.getTeam().getName());
    }

    @Test
    public void testGetNoCompanyOrTeam() {
        Counterparty counterparty = counterpartyDao.get(2);
        assertNotNull(counterparty);
        assertNull(counterparty.getFund());
        assertNull(counterparty.getTeam());
    }
}
