package org.kd.main.server.model.data;

import org.kd.main.common.entities.Account;
import org.kd.main.common.entities.CorporateAccount;
import org.kd.main.common.model.data.DataService;
import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Transfer;
import org.kd.main.server.model.data.dao.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DataServiceImpl implements DataService {

    private final AccountDaoRepo accountDaoRepo;

    private final BankDaoRepo bankDaoRepo;

    private final TransferDaoRepo transferDaoRepo;

    @Autowired
    public DataServiceImpl(AccountDaoRepo accountDaoRepo, BankDaoRepo bankDaoRepo, TransferDaoRepo transferDaoRepo){
        this.bankDaoRepo = bankDaoRepo;
        this.accountDaoRepo = accountDaoRepo;
        this.transferDaoRepo = transferDaoRepo;
    }

    @Override
    public void initApplication() {
    }

    @Override
    public void saveDb() {
    }

    @Override
    public List<CorporateAccount> readCustomers() {
        return accountDaoRepo.readAllCorporate();
    }

    @Override
    public List<Bank> readBanks() {

        return bankDaoRepo.readAll();
    }

    @Override
    public List<Transfer> readTransfers() {
        return transferDaoRepo.readAll();
    }

    @Override
    public void updateBank(Bank bank) {
        bankDaoRepo.update(bank);
    }

    @Override
    public Optional<Account> readCustomer(long id) {
        return accountDaoRepo.read(id);
    }

    @Override
    public void updateCustomer(Account account) {
        accountDaoRepo.update(account);
    }

    @Override
    public Bank readBank(long id) {
        return bankDaoRepo.read(id);
    }
}
