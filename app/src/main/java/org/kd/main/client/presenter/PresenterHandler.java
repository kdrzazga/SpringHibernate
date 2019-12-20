package org.kd.main.client.presenter;

import org.kd.main.common.entities.Account;
import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Transfer;

import java.util.List;
import java.util.Optional;

public interface PresenterHandler {

    boolean createBank(String name, String shortname);

    Optional<Bank> readBank(Long id);

    List<Bank> readBanks();

    boolean updateBank(Bank bank);

    boolean deleteBank(Long id);

    boolean createAccount(String name, String shortname, Double units, Long bankId);

    Optional<Account> readAccount(Long id);

    List<Account> readAccounts();

    boolean updateAccount(Account account);

    boolean deleteAccount(Long id);

    boolean bookTransfer();

    List<Transfer> readTransfers();

    boolean deleteTransfer(Long id);
    
    void initApplication();

    boolean saveDb();

    void stopServer();

    void setAccountId(Long accountId);

    Long getAccountId();
}
