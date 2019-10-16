package org.kd.main.server.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kd.main.common.entities.Bank;
import org.kd.main.server.model.data.dao.BankDaoRepo;
import org.kd.main.server.model.data.dao.CustomerDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankController {

    @Autowired
    private CustomerDaoRepo customerDao;

    @Autowired
    private BankDaoRepo bankDao;

    @PostMapping(path = "/bank", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createBank(@RequestBody String bankJson) {
        try {
            bankJson = bankJson
                    .replaceAll("\r\n", "")
                    .replaceAll("\t", "");

            var bank = new ObjectMapper().readValue(bankJson, Bank.class);
            bankDao.create(bank);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(bank.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Couldn't create bank " + bankJson);
        }
    }

    @GetMapping(path = "/bank/{id}", produces = "application/json")
    public ResponseEntity<Bank> readBank(@PathVariable long id) {
        var bank = bankDao.read(id);

        return bank != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(bank)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Couldn't read bank with id = " + id)
                        .build();
    }

    @GetMapping(path = "/banks")
    public ResponseEntity<List<Bank>> readBanks() {
        var allBanks = bankDao.readAll();

        return allBanks != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(allBanks)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of Banks")
                        .build();
    }

    @PutMapping(path = "/bank", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateBank(@RequestBody String bankJson) {
        try {
            bankJson = bankJson
                    .replaceAll("\r\n", "")
                    .replaceAll("\t", "");

            var bank = new ObjectMapper().readValue(bankJson, Bank.class);
            bankDao.update(bank);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(bank.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Couldn't update bank " + bankJson);
        }
    }

    @DeleteMapping(path = "/bank/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return (bankDao.deleteWithFkNulling(id))
                ? ResponseEntity
                .status(HttpStatus.OK)
                .body("Bank " + id + " deleted. All bank customers are not related now.")

                : ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Couldn't delete bank with id = " + id);
    }
}
