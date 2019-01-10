package org.kd.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PartyDaoImplTest extends BaseDaoTest {

    @Autowired
    private PartyDao partyDao;

    @Test
    public void testGet() {
        var party = partyDao.get(1);
        assertNotNull(party);
        assertEquals("Employees Provident Fund", party.getName());
    }

    @Test
    public void testGetCompanyWithNoEmployees() {
        var party = partyDao.get(2);
        assertNotNull(party);
    }
}
