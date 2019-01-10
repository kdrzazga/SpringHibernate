package org.kd.dao;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.kd.db.DbManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-context.xml")
public abstract class BaseDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private IDatabaseConnection databaseConnection;

    @Autowired
    private DbManager dbManager;

    @Before
    public void setUp() throws SQLException, DatabaseUnitException {
        dbManager.setupDb(databaseConnection);
    }

    @After
    public void tearDown() throws SQLException, DatabaseUnitException {
        dbManager.tearDownDb(databaseConnection);
        databaseConnection.close();
    }
}
