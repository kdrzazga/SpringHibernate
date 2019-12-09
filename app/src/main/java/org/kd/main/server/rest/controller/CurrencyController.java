package org.kd.main.server.rest.controller;

import org.kd.main.common.entities.Currency;
import org.kd.main.server.model.data.dao.CurrencyDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
class CurrencyController {

    private final CurrencyDaoRepo currencyDaoRepo;

    @Autowired
    public CurrencyController(CurrencyDaoRepo currencyDaoRepo) {
        this.currencyDaoRepo = currencyDaoRepo;
    }

    @GetMapping(path = "/currency/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Currency> read(@PathVariable long id) {
        var currency = currencyDaoRepo.read(id);

        return currency != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(currency)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Couldn't read " + Currency.class.getSimpleName() + " with id = " + id)
                        .build();
    }

    @GetMapping(path = "/currencies")
    public ResponseEntity<List<Currency>> readAll() {
        var currencyList = currencyDaoRepo.readAll();

        return currencyList != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(currencyList)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of " + Currency.class.getSimpleName())
                        .build();
    }
}
