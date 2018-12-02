package org.kd.runastest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.dao.CounterpartyDao;
import org.kd.entities.Counterparty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class Application extends BaseApplication
{

    @Autowired
    private CounterpartyDao counterpartyDao;

    @Test
    public void testGet() {
        Counterparty counterparty = counterpartyDao.get(1);
        assertNotNull(counterparty);
        assertEquals("Bob", counterparty.getFirstName());


    }
    }