package org.kd.main.client.presenter;

import org.kd.main.common.entities.Account;
import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Transfer;

import java.util.List;

public interface PresenterHandler {

    void createBank(String name, String shortname);

    Bank readBank(Long id);

    List<Bank> readBanks();

    void updateBank(Bank bank);

    void deleteBank(Long id);

    void createCustomer(String name, String shortname, Double units, Long bankId);

    Account readCustomer(Long id);

    List<Account> readCustomers();

    void updateCustomer(Account account);

    void deleteCustomer(Long id);

    void bookTransfer();

    List<Transfer> readTransfers();

    void deleteTransfer(Long id);
    
    void initApplication();

    void saveDb();

    void stopServer();
}
