package org.kd.main.client.presenter;

import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Fund;
import org.kd.main.common.entities.Transfer;

import java.util.List;

public interface PresenterHandler {

    List<Bank> loadBanks();

    void saveBank(Bank bank);

    Bank loadBank(long id);

    List<Fund> loadFunds();

    Fund loadFund(long id);

    void saveFund(Fund fund);

    List<Transfer> loadTransfers();

    void initApplication();

    void saveDb();
}
