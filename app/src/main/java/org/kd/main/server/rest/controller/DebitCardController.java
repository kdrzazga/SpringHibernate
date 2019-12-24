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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
class DebitCardController {

    private final DebitCardDaoRepo debitCardDao;

    @Autowired
    public DebitCardController(DebitCardDaoRepo debitCardDao) {
        this.debitCardDao = debitCardDao;
    }

    @GetMapping(path = "/debitcard/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DebitCard> read(@PathVariable long id) {
        var debitCard = debitCardDao.read(id);

        return debitCard != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Access-Control-Allow-Origin", "*")
                        .body(debitCard)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("message", "Couldn't read " + DebitCard.class.getSimpleName() + " with id = " + id)
                        .build();
    }

    @GetMapping(path = "/debitcards")
    public ResponseEntity<List<DebitCard>> readAll() {
        var debitCards = debitCardDao.readAll();

        return debitCards != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Access-Control-Allow-Origin", "*")
                        .body(debitCards)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("message", "Error reading list of " + DebitCard.class.getSimpleName())
                        .build();
    }

    @GetMapping(path = "/debitcards/{accountId}")
    public ResponseEntity<List<DebitCard>> readAccountCards(@PathVariable Long accountId) {
        var debitCards = debitCardDao.readAccountCards(accountId);

        return debitCards != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Access-Control-Allow-Origin", "*")
                        .body(debitCards)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("message", "Error reading list of "
                                + DebitCard.class.getSimpleName() + "for account " + accountId)
                        .build();
    }
}
