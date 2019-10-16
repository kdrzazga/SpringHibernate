package org.kd.main.server.rest.controller;

import org.kd.main.common.entities.Credit;
import org.kd.main.server.model.data.dao.CreditDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreditController {

    @Autowired
    private CreditDaoRepo creditCardDao;

    @GetMapping(path = "/credit/{id}", produces = "application/json")
    public ResponseEntity<Credit> readCredit(@PathVariable long id) {
        var city = creditCardDao.read(id);

        return city != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(city)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Couldn't read " + Credit.class.getSimpleName() + " with id = " + id)
                        .build();
    }

    @GetMapping(path = "/credits")
    public ResponseEntity<List<Credit>> readCredits() {
        var allCountries = creditCardDao.readAll();

        return allCountries != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(allCountries)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of " + Credit.class.getSimpleName())
                        .build();
    }
}
