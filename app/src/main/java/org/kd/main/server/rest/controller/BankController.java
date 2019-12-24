package org.kd.main.server.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kd.main.common.RestUtility;
import org.kd.main.common.entities.Account;
import org.kd.main.common.entities.Bank;
import org.kd.main.server.model.data.dao.BankDaoRepo;
import org.kd.main.server.model.data.dao.AccountDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
class BankController {

    @Autowired
    private AccountDaoRepo accountDaoRepo;

    @Autowired
    private BankDaoRepo bankDao;

    @Autowired
    private RestUtility restUtility;

    @PostMapping(path = "/bank", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody String bankJson) {
        try {
            bankJson = bankJson
                    .replaceAll("\r\n", "")
                    .replaceAll("\t", "");

            var bank = new ObjectMapper().readValue(bankJson, Bank.class);
            bankDao.create(bank);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Access-Control-Allow-Origin", "*")
                    .body(bank.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "*")
                    .body("Couldn't create bank " + bankJson);
        }
    }

    @GetMapping(path = "/bank/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Bank> read(@PathVariable long id) {
        var bank = bankDao.read(id);

        return bank != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Access-Control-Allow-Origin", "*")
                        .body(bank)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("message", "Couldn't read bank with id = " + id)
                        .build();
    }

    @GetMapping(path = "/banks")
    public ResponseEntity<List<Bank>> readAll() {
        var allBanks = bankDao.readAll();

        return allBanks != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Access-Control-Allow-Origin", "*")
                        .body(allBanks)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("message", "Error reading list of Banks")
                        .build();
    }

    @GetMapping(path = "/associatedAccounts/{bankId}")
    public ResponseEntity<List<Account>> getAssociatedAccounts(@PathVariable Long bankId) {
        var associatedAccounts = accountDaoRepo.readAccountsOfBank(bankId);

        return associatedAccounts.size() > 0 ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Access-Control-Allow-Origin", "*")
                        .body(associatedAccounts)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("message", "Error reading associated accounts of bank with id" + bankId)
                        .build();
    }


    @PutMapping(path = "/bank", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody String bankJson) {
        try {
            bankJson = bankJson
                    .replaceAll("\r\n", "")
                    .replaceAll("\t", "");

            var bank = new ObjectMapper().readValue(bankJson, Bank.class);
            bankDao.update(bank);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Access-Control-Allow-Origin", "*")
                    .body(bank.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "*")
                    .body("Couldn't update bank " + bankJson);
        }
    }

    @DeleteMapping(path = "/bank/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return (bankDao.deleteWithFkNulling(id))
                ? ResponseEntity
                .status(HttpStatus.OK)
                .header("Access-Control-Allow-Origin", "*")
                .body("Bank " + id + " deleted. All bank accounts are not related now.")

                : ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("Access-Control-Allow-Origin", "*")
                .body("Couldn't delete bank with id = " + id);
    }
}
