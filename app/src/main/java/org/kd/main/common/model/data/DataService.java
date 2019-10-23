package org.kd.main.common.model.data;

import org.kd.main.common.entities.Customer;
import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Transfer;

import java.util.List;

public interface DataService {

    void initApplication();

    /**
     * Only optionally applicable to in memory databases
     */
    void saveDb();

    List<Customer> readCustomers();

    List<Bank> readBanks();

    List<Transfer> readTransfers();

    void updateBank(Bank bank);

    Customer readCustomer(long id);

    void updateCustomer(Customer customer);

    Bank readBank(long id);
}
