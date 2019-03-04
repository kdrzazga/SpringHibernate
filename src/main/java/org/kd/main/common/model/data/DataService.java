package org.kd.main.common.model.data;

import org.kd.main.server.model.data.entities.Fund;
import org.kd.main.server.model.data.entities.Party;
import org.kd.main.server.model.data.entities.Trade;

import java.util.List;

public interface DataService {

    void initApplication();

    /**
     * Only optionally applicable to in memory databases
     */
    void saveDb();

    List<Fund> loadFunds();

    List<Party> loadParties();

    List<Trade> loadTrades();

    void saveParty(Party party);

    Fund loadFund(long id);

    void saveFund(Fund fund);

    Party loadParty(long id);
}
