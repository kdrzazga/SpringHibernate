package org.kd.main.server.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kd.main.common.entities.Customer;
import org.kd.main.server.model.data.dao.CustomerDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerDaoRepo customerDao;

    @PostMapping(path = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createCustomer(@RequestBody String customerJson) {
        var objectMapper = new ObjectMapper();

        ResponseEntity<String> errorResponse = ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("message", "Couldn't create")
                .build();

        try {
            Customer customer = objectMapper.readValue(customerJson, Customer.class);
            customer = customerDao.create(customer);

            return (customer != null) ?
                    ResponseEntity
                            .status(HttpStatus.OK)
                            .body(customer.toString())
                    :
                    errorResponse;
        } catch (IOException e) {
            return errorResponse;
        }
    }

    @GetMapping(path = "/customer/{id}", produces = "application/json")
    public ResponseEntity<Customer> readCustomer(@PathVariable long id) {
        var customer = customerDao.read(id);

        return (customer != null)
                ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(customer)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Couldn't read customer with id = " + id)
                        .build();
    }

    @GetMapping(path = "/customers")
    public ResponseEntity<List<Customer>> readCustomers() {
        var allCustomers = customerDao.readAll();

        return allCustomers != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(allCustomers)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of Customers")
                        .build();

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
                    .body("Error updateing customer" + customerJson);
        }
    }

    @DeleteMapping(path = "/customer/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return (customerDao.delete(id) != null)
                ? ResponseEntity
                .status(HttpStatus.OK)
                .body("Customer " + id + " deleted.")

                : ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Couldn't delete customer with id = " + id);
    }
}
