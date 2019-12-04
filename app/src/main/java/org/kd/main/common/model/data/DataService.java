package org.kd.main.common.model.data;

import org.kd.main.common.entities.Account;
import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.CorporateAccount;
import org.kd.main.common.entities.Transfer;

import java.util.List;

public interface DataService {

    void initApplication();

    /**
     * Only optionally applicable to in memory databases
     */
    void saveDb();

    List<CorporateAccount> readCustomers();

    List<Bank> readBanks();

    List<Transfer> readTransfers();

    void updateBank(Bank bank);

    Account readCustomer(long id);

    void updateCustomer(Account account);

    Bank readBank(long id);
}
