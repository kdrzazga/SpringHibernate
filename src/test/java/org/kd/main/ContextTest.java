package org.kd.main;

import org.dbunit.database.IDatabaseConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.main.model.data.dao.FundDao;
import org.kd.main.model.data.db.DbManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-context.xml")
public class ContextTest {

    @Autowired
    private IDatabaseConnection databaseConnection;

    @Autowired
    private DbManager dbManager;

    @Autowired
    private FundDao fundDao;

    @Autowired
    private FundDao partyDao;

    @Autowired
    private FundDao tradeDao;

    @Test
    public void testDatabaseConnectionInjection() {
        assertNotNull(databaseConnection);
    }

    @Test
    public void testDbManagerInjection() {
        assertNotNull(dbManager);
    }

    @Test
    public void testDatabasePathInjection() {
        assertNotNull(dbManager.getDbFilename());
        assertTrue(dbManager.getDbFilename().endsWith(".xml"));
    }

    @Test
    public void FundDaoInjection() {
        assertNotNull(fundDao);
    }

    @Test
    public void PartyDaoInjection() {
        assertNotNull(partyDao);
    }

    @Test
    public void TradeDaoInjection() {
        assertNotNull(tradeDao);
    }
}