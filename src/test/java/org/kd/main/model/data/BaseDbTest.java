package org.kd.main.model.data;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.kd.main.model.data.DataModelManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-context.xml")
public abstract class BaseDbTest extends AbstractTransactionalJUnit4SpringContextTests {

    @BeforeClass
    public static void setUp() {
        DataModelManager.initApplication();
    }

    @AfterClass
    public static void tearDown() {
        DataModelManager.clearDb();
    }
}
