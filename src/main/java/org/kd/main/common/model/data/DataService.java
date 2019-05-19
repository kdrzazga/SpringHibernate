package org.kd.main.common.model.data;

import org.kd.main.common.entities.Fund;
import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Transfer;

import java.util.List;

public interface DataService {

    void initApplication();

    /**
     * Only optionally applicable to in memory databases
     */
    void saveDb();

    List<Fund> loadCustomers();

    List<Bank> loadBanks();

    List<Transfer> loadTransfers();

    void saveBank(Bank bank);

    Fund loadCustomer(long id);

    void saveCustomer(Fund fund);

    Bank loadBank(long id);
}
