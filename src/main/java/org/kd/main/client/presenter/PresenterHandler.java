package org.kd.main.client.presenter;

import org.kd.main.server.model.data.entities.Fund;
import org.kd.main.server.model.data.entities.Party;
import org.kd.main.server.model.data.entities.Trade;

import java.util.List;

public interface PresenterHandler {

    List<Party> loadParties();

    void saveParty(Party party);

    List<Fund> loadFunds();

    Fund loadFund(long id);

    void saveFund(Fund fund);

    List<Trade> loadTrades();

    void initApplication();

    void saveDb();

    Party loadParty(long id);
}
