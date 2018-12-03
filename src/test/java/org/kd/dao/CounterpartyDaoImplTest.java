package org.kd.dao;

import org.kd.entities.Counterparty;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class CounterpartyDaoImplTest extends BaseDaoTest {

    @Autowired
    private CounterpartyDao counterpartyDao;

    @Test
    public void testGet() {
        Counterparty counterparty = counterpartyDao.get(1);
        assertNotNull(counterparty);
        assertEquals("Spacetek", counterparty.getName());
        assertEquals(2, counterparty.getFunds().size());
    }

    @Test
    public void testGetCompanyWithNoEmployees() {
        Counterparty counterparty = counterpartyDao.get(2);
        assertNotNull(counterparty);
        assertNotNull(counterparty.getFunds());
        assertTrue(counterparty.getFunds().isEmpty());
    }
}
