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

    private final CreditDaoRepo creditDaoRepo;

    @Autowired
    public CreditController(CreditDaoRepo creditDaoRepo) {
        this.creditDaoRepo = creditDaoRepo;
    }

    @GetMapping(path = "/credit/{id}", produces = "application/json")
    public ResponseEntity<Credit> readCredit(@PathVariable long id) {
        var credit = creditDaoRepo.read(id);

        return credit != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(credit)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Couldn't read " + Credit.class.getSimpleName() + " with id = " + id)
                        .build();
    }

    @GetMapping(path = "/credits")
    public ResponseEntity<List<Credit>> readCredits() {
        var creditList = creditDaoRepo.readAll();

        return creditList != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(creditList)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of " + Credit.class.getSimpleName())
                        .build();
    }
}
