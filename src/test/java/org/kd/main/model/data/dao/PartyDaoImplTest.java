package org.kd.main.model.data.dao;

import org.junit.Test;
import org.kd.main.model.data.BaseDbTest;
import org.kd.main.model.data.DataModelManager;

import static org.junit.Assert.*;

public class PartyDaoImplTest extends BaseDbTest {

    @Test
    public void testGet() {
        var party = DataModelManager.getPartyDao().get(2100);
        var possibleFailureCause = "Please first check if DB was changed.";
        assertNotNull(possibleFailureCause, party);
        assertEquals(possibleFailureCause, "South Korea National Pension Service", party.getName());
    }

}
