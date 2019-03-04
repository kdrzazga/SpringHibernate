package org.kd.main.server.model.data;

import org.kd.main.common.model.data.DataService;
import org.kd.main.server.model.data.entities.Fund;
import org.kd.main.server.model.data.entities.Party;
import org.kd.main.server.model.data.entities.Trade;
import org.kd.main.server.model.data.dao.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DataServiceImpl implements DataService {

    @Autowired
    private FundDaoRepo fundDao;

    @Autowired
    private PartyDaoRepo partyDao;

    @Autowired
    private TradeDaoRepo tradeDao;

    @Override
    public void initApplication() {
    }

    @Override
    public void saveDb() {
    }

    @Override
    public List<Fund> loadFunds() {
        return fundDao.getAllFunds();
    }

    @Override
    public List<Party> loadParties() {
        return partyDao.getAllParties();
    }

    @Override
    public List<Trade> loadTrades() {
        return tradeDao.getAllTrades();
    }

    @Override
    public void saveParty(Party party) {
        partyDao.update(party);
    }

    @Override
    public Fund loadFund(long id) {
        return fundDao.get(id);
    }

    @Override
    public void saveFund(Fund fund) {
        fundDao.update(fund);
    }

    @Override
    public Party loadParty(long id) {
        return partyDao.get(id);
    }
}
