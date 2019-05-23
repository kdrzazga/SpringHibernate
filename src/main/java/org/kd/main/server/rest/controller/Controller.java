package org.kd.main.server.rest.controller;

import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Customer;
import org.kd.main.common.entities.Transfer;
import org.kd.main.server.model.data.dao.BankDaoRepo;
import org.kd.main.server.model.data.dao.FundDaoRepo;
import org.kd.main.server.model.data.dao.TransferDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private FundDaoRepo customerDao;

    @Autowired
    private BankDaoRepo bankDao;

    @Autowired
    private TransferDaoRepo transferDao;

    @GetMapping(path = "/customers")
    public List<Customer> readCustomers() {
        return customerDao.getAllCustomers();
    }

    @GetMapping(path = "/banks")
    public List<Bank> readBanks() {
        return bankDao.getAllBanks();
    }

    @GetMapping(path = "/bank/{id}")
    public Bank readBank(@PathVariable long id) {
        return bankDao.get(id);
    }

    @GetMapping(path = "/customer/{id}")
    public Customer readCustomer(@PathVariable long id) {
        return customerDao.get(id);
    }

    @GetMapping(path = "/transfers")
    public List<Transfer> readTransfers() {
        return transferDao.getAllTransfers();
    }

    @PostMapping(path = "/customer")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        customerDao.update(customer);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customer);
    }

    @PostMapping(path = "/bank")
    public ResponseEntity<Bank> updateBank(@RequestBody Bank bank) {
        bankDao.update(bank);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bank);
    }

}
