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

    boolean deleteBank(Long id);

    void createAccount(String name, String shortname, Double units, Long bankId);

    Account readAccount(Long id);

    List<Account> readAccounts();

    void updateAccount(Account account);

    void deleteAccount(Long id);

    boolean bookTransfer();

    List<Transfer> readTransfers();

    void deleteTransfer(Long id);
    
    void initApplication();

    void saveDb();

    void stopServer();

    void setAccountId(Long accountId);

    Long getAccountId();
}
