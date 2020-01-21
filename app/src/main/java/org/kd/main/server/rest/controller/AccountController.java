package org.kd.main.server.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kd.main.common.RestUtility;
import org.kd.main.common.entities.CorporateAccount;
import org.kd.main.common.entities.Account;
import org.kd.main.common.entities.IndividualAccount;
import org.kd.main.server.model.data.dao.AccountDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.TEXT_PLAIN_VALUE;

@RestController
class AccountController {

    private final AccountDaoRepo accountDaoRepo;
    private final RestUtility restUtility;

    @Autowired
    public AccountController(AccountDaoRepo accountDaoRepo, RestUtility restUtility) {
        this.accountDaoRepo = accountDaoRepo;
        this.restUtility = restUtility;
    }

    @PostMapping(path = "/account", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody String customerJson) {
        var objectMapper = new ObjectMapper();

        ResponseEntity<String> errorResponse = ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("Access-Control-Allow-Origin", "*")
                .header("message", "Couldn't create")
                .build();

        try {
            var account = objectMapper.readValue(customerJson, Account.class);
            account = accountDaoRepo.create(account);

            return (account != null) ?
                    ResponseEntity
                            .status(HttpStatus.OK)
                            .header("Access-Control-Allow-Origin", "*")
                            .body(account.toString())
                    :
                    errorResponse;
        } catch (IOException e) {
            return errorResponse;
        }
    }

    @GetMapping(path = "/account/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Account>> read(@PathVariable long id) {
        var account = accountDaoRepo.read(id);

        return (account.isPresent())
                ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Access-Control-Allow-Origin", "*")
                        .body(account)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("message", "Couldn't read account with id = " + id)
                        .build();
    }

    @GetMapping(path = "/corporate-accounts")
    public ResponseEntity<List<CorporateAccount>> readAllCorporate() {
        var corporateAccounts = accountDaoRepo.readAllCorporate();

        return corporateAccounts != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Access-Control-Allow-Origin", "*")
                        .body(corporateAccounts)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("message", "Error reading list of Corporate Customers")
                        .build();
    }

    @GetMapping(path = "/individual-accounts")
    public ResponseEntity<List<IndividualAccount>> readAllIndividual() {
        var accounts = accountDaoRepo.readAllIndividual();

        return accounts != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Access-Control-Allow-Origin", "*")
                        .body(accounts)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("message", "Error reading list of Individual Customers")
                        .build();
    }

    @PutMapping(path = "/account", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody String accountJson) {

        ResponseEntity<String> result;
        String logMessage;

        try {
            var objectMapper = new ObjectMapper();
            accountJson = accountJson
                    .replaceAll("\r\n", "")
                    .replaceAll("\t", "");

            var account = objectMapper.readValue(accountJson, Account.class);
            accountDaoRepo.update(account);

            result = ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Access-Control-Allow-Origin", "*")
                    .body(account.toString());

            logMessage = "Updated account " + account.toString();

        } catch (Exception e) {
            e.printStackTrace();

            logMessage = "Error updating account" + accountJson;

            result = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "*")
                    .body(logMessage);
        }

        log(logMessage);
        return result;

    }

    @DeleteMapping(path = "/account/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        var deletedAccount = accountDaoRepo.delete(id);
        String resultMessage;
        ResponseEntity<String> result;

        if (deletedAccount.isPresent()) {
            resultMessage = "Account " + id + " deleted.";

            result = ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Access-Control-Allow-Origin", "*")
                    .body(resultMessage);
        } else {
            resultMessage = "Couldn't delete account with id = " + id;
            result = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "*")
                    .body(resultMessage);
        }

        log(resultMessage);
        return result;
    }

    private void log(String message) {
        restUtility.processHttpRequest(HttpMethod.POST, message,
                "http://localhost:8080/addEntryToRecentLog", TEXT_PLAIN_VALUE);
    }
}
