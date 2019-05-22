package org.kd.main.client.presenter;

import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Customer;
import org.kd.main.common.entities.Transfer;

import java.util.List;

public interface PresenterHandler {

    List<Bank> loadBanks();

    void saveBank(Bank bank);

    Bank loadBank(long id);

    List<Customer> loadCustomers();

    Customer loadFund(long id);

    void saveFund(Customer customer);

    List<Transfer> loadTransfers();

    void initApplication();

    void saveDb();
}
