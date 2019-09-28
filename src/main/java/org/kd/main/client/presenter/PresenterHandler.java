package org.kd.main.client.presenter;

import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Customer;
import org.kd.main.common.entities.Transfer;

import java.util.List;

public interface PresenterHandler {

    List<Bank> readBanks();

    void saveBank(Bank bank);

    Bank readBank(long id);

    List<Customer> readCustomers();

    Customer readCustomer(long id);

    void saveCustomer(Customer customer);

    List<Transfer> readTransfers();

    void initApplication();

    void saveDb();
}
