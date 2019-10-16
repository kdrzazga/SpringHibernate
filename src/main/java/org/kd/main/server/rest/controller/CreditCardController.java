package org.kd.main.server.rest.controller;

import org.kd.main.common.entities.CreditCard;
import org.kd.main.server.model.data.dao.CreditCardDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreditCardController {

    @Autowired
    private CreditCardDaoRepo creditCardDao;

    @GetMapping(path = "/creditcard/{id}", produces = "application/json")
    public ResponseEntity<CreditCard> readCreditCard(@PathVariable long id) {
        var city = creditCardDao.read(id);

        return city != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(city)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Couldn't read " + CreditCard.class.getSimpleName() + " with id = " + id)
                        .build();
    }

    @GetMapping(path = "/creditcards")
    public ResponseEntity<List<CreditCard>> readCreditCards() {
        var allCountries = creditCardDao.readAll();

        return allCountries != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(allCountries)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of " + CreditCard.class.getSimpleName())
                        .build();
    }
}
