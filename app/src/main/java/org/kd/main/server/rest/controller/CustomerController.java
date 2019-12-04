package org.kd.main.server.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kd.main.common.entities.CorporateAccount;
import org.kd.main.common.entities.Account;
import org.kd.main.common.entities.IndividualAccount;
import org.kd.main.server.model.data.dao.CustomerDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class CustomerController {

    private final CustomerDaoRepo customerDao;

    @Autowired
    public CustomerController(CustomerDaoRepo customerDao) {
        this.customerDao = customerDao;
    }

    @PostMapping(path = "/account", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createCustomer(@RequestBody String customerJson) {
        var objectMapper = new ObjectMapper();

        ResponseEntity<String> errorResponse = ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("message", "Couldn't create")
                .build();

        try {
            Account account = objectMapper.readValue(customerJson, Account.class);
            account = customerDao.create(account);

            return (account != null) ?
                    ResponseEntity
                            .status(HttpStatus.OK)
                            .body(account.toString())
                    :
                    errorResponse;
        } catch (IOException e) {
            return errorResponse;
        }
    }

    @GetMapping(path = "/account/{id}", produces = "application/json")
    public ResponseEntity<Account> readCustomer(@PathVariable long id) {
        var customer = customerDao.read(id);

        return (customer != null)
                ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(customer)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Couldn't read account with id = " + id)
                        .build();
    }

    @GetMapping(path = "/corporate-accounts")
    public ResponseEntity<List<CorporateAccount>> readCorpotateCustomers() {
        var allCustomers = customerDao.readAllCorporate();

        return allCustomers != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(allCustomers)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of Corporate Customers")
                        .build();
    }

    @GetMapping(path = "/individual-accounts")
    public ResponseEntity<List<IndividualAccount>> readIndividualCustomers() {
        var allCustomers = customerDao.readAllIndividual();

        return allCustomers != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(allCustomers)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of Individual Customers")
                        .build();

    }

    @PutMapping(path = "/account", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateCustomer(@RequestBody String customerJson) {
        try {
            var objectMapper = new ObjectMapper();
            customerJson = customerJson
                    .replaceAll("\r\n", "")
                    .replaceAll("\t", "");

            var customer = objectMapper.readValue(customerJson, Account.class);
            customerDao.update(customer);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(customer.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updateing account" + customerJson);
        }
    }

    @DeleteMapping(path = "/account/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return (customerDao.delete(id) != null)
                ? ResponseEntity
                .status(HttpStatus.OK)
                .body("Account " + id + " deleted.")

                : ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Couldn't delete account with id = " + id);
    }
}
