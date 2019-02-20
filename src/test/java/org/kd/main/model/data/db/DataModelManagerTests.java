package org.kd.main.model.data.db;

import org.junit.Test;
import org.kd.main.model.data.DataModelManager;
import org.kd.main.model.data.BaseDbTest;

import static org.junit.Assert.assertNotNull;

public class DataModelManagerTests extends BaseDbTest {

    @Test
    public void testContextInitializationInDataModelManager() {
        assertNotNull(DataModelManager.getContext());
    }

    @Test
    public void testFundDaoInitializationInDataModelManager() {
        assertNotNull(DataModelManager.getFundDao());
    }

    @Test
    public void testPartyDaoInitializationInDataModelManager() {
        assertNotNull(DataModelManager.getPartyDao());
    }

    @Test
    public void testTradeDaoInitializationInDataModelManager() {
        assertNotNull(DataModelManager.getTradeDao());
    }

}
