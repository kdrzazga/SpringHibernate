package org.kd.main.client.presenter;

import org.kd.main.common.model.data.DataService;
import org.kd.main.server.model.data.entities.Fund;
import org.kd.main.server.model.data.entities.Party;
import org.kd.main.server.model.data.entities.Trade;
import org.kd.main.server.model.data.DataServiceImpl;

import java.util.List;

public class TraderPresenter implements PresenterHandler {

    private final DataService dataService = new DataServiceImpl();//TODO DataServiceImpl needs to replaced with rest call

    @Override
    public List<Party> loadParties() {
        return dataService.loadParties();
    }

    @Override
    public Party loadParty(long id) {
        return dataService.loadParty(id);
    }

    @Override
    public void saveParty(Party party) {
        dataService.saveParty(party);
    }

    @Override
    public List<Fund> loadFunds() {
        return dataService.loadFunds();
    }

    @Override
    public Fund loadFund(long id) {
        return dataService.loadFund(id);
    }

    @Override
    public void saveFund(Fund fund) {
        dataService.saveFund(fund);
    }

    @Override
    public List<Trade> loadTrades() {
        return dataService.loadTrades();
    }

    @Override
    public void initApplication() {
        dataService.initApplication();
    }

    @Override
    public void saveDb() {
        dataService.saveDb();
    }

}
