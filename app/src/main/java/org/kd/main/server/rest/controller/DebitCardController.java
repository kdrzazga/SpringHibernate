package org.kd.main.server.rest.controller;

import org.kd.main.common.entities.DebitCard;
import org.kd.main.server.model.data.dao.DebitCardDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DebitCardController {

    private final DebitCardDaoRepo debitCardDao;

    @Autowired
    public DebitCardController(DebitCardDaoRepo debitCardDao) {
        this.debitCardDao = debitCardDao;
    }

    @GetMapping(path = "/debitcard/{id}", produces = "application/json")
    public ResponseEntity<DebitCard> readDebitCard(@PathVariable long id) {
        var debitCard = debitCardDao.read(id);

        return debitCard != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(debitCard)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Couldn't read " + DebitCard.class.getSimpleName() + " with id = " + id)
                        .build();
    }

    @GetMapping(path = "/debitcards")
    public ResponseEntity<List<DebitCard>> readDebitCards() {
        var debitCards = debitCardDao.readAll();

        return debitCards != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(debitCards)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of " + DebitCard.class.getSimpleName())
                        .build();
    }
}
