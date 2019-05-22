package org.kd.main.server.model.data;

import org.kd.main.common.entities.Customer;
import org.kd.main.common.model.data.DataService;
import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Transfer;
import org.kd.main.server.model.data.dao.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DataServiceImpl implements DataService {

    private FundDaoRepo customerDaoRepo;

    private BankDaoRepo bankDaoRepo;

    private TransferDaoRepo transferDaoRepo;

    @Autowired
    public DataServiceImpl(FundDaoRepo customerDaoRepo, BankDaoRepo bankDaoRepo, TransferDaoRepo transferDaoRepo){
        this.bankDaoRepo = bankDaoRepo;
        this.customerDaoRepo = customerDaoRepo;
        this.transferDaoRepo = transferDaoRepo;
    }

    @Override
    public void initApplication() {
    }

    @Override
    public void saveDb() {
    }

    @Override
    public List<Customer> loadCustomers() {
        return customerDaoRepo.getAllFunds();
    }

    @Override
    public List<Bank> loadBanks() {

        return bankDaoRepo.getAllBanks();
    }

    @Override
    public List<Transfer> loadTransfers() {
        return transferDaoRepo.getAllTransfers();
    }

    @Override
    public void saveBank(Bank bank) {
        bankDaoRepo.update(bank);
    }

    @Override
    public Customer loadCustomer(long id) {
        return customerDaoRepo.get(id);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerDaoRepo.update(customer);
    }

    @Override
    public Bank loadBank(long id) {
        return bankDaoRepo.get(id);
    }
}
