package org.kd.main.server.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @GetMapping(path = "/customer/{id}")
    public Customer readCustomer(@PathVariable long id) {
        return customerDao.get(id);
    }

    @GetMapping(path = "/customers")
    public List<Customer> readCustomers() {
        return customerDao.getAllCustomers();
    }

    @PutMapping(path = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateCustomer(@RequestBody String customerJson) {
        try {
            var objectMapper = new ObjectMapper();
            customerJson = customerJson
                    .replaceAll("\r\n", "")
                    .replaceAll("\t", "");

            var customer = objectMapper.readValue(customerJson, Customer.class);
            customerDao.update(customer);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(customer.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ERROR");
        }
    }

    @GetMapping(path = "/bank/{id}")
    public Bank readBank(@PathVariable long id) {
        return bankDao.get(id);
    }

    @GetMapping(path = "/banks")
    public List<Bank> readBanks() {
        return bankDao.getAllBanks();
    }

    @PutMapping(path = "/bank", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateBank(@RequestBody String bankJson) {
        try {
            var objectMapper = new ObjectMapper();
            bankJson = bankJson
                    .replaceAll("\r\n", "")
                    .replaceAll("\t", "");

            var bank = objectMapper.readValue(bankJson, Bank.class);
            bankDao.update(bank);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(bank.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ERROR");
        }

    }

    @GetMapping(path = "/transfer/{id}")
    public Transfer readTransfer(@PathVariable long id) {
        return transferDao.getTransferByPrimaryKey(id);
    }

    @GetMapping(path = "/transfers")
    public List<Transfer> readTransfers() {
        return transferDao.getAllTransfers();
    }


}
