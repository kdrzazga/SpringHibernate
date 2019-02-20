package org.kd.main.model.data.dao;

import org.junit.Test;
import org.kd.main.model.data.BaseDbTest;
import org.kd.main.model.data.DataModelManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FundDaoImplTest extends BaseDbTest {

    @Test
    public void testGet() {
        var fund = DataModelManager.getFundDao().get(3016);
        var possibleFailureCause = "Please first check if DB was changed.";

        assertNotNull(possibleFailureCause, fund);
        assertEquals(possibleFailureCause, "IBM", fund.getName());
        assertEquals(100, fund.getTrade().getQuantity(), 0.01);
    }

}
