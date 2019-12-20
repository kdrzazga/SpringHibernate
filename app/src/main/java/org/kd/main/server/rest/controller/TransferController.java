package org.kd.main.server.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kd.main.common.RestUtility;
import org.kd.main.common.entities.Transfer;
import org.kd.main.server.model.data.dao.TransferDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.TEXT_PLAIN_VALUE;

@RestController
class TransferController {

    private final TransferDaoRepo transferDao;
    private final RestUtility restUtility;

    @Autowired
    public TransferController(TransferDaoRepo transferDao, RestUtility restUtility) {
        this.transferDao = transferDao;
        this.restUtility = restUtility;
    }

    @PostMapping(path = "/transfer", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> book(@RequestBody String transferJson) {

        try {
            var objectMapper = new ObjectMapper();
            transferJson = transferJson
                    .replaceAll("\r\n", "")
                    .replaceAll("\t", "");

            var newTransfer = objectMapper.readValue(transferJson, Transfer.class);

            var newId = transferDao.book(newTransfer.getSrcAccount(), newTransfer.getDestAccount(), newTransfer.getUnits());

            logTransfer(newId, newTransfer);

            return newId >= 0 ?
                    ResponseEntity
                            .status(HttpStatus.OK)
                            .body("Booked with id=" + newId)
                    :
                    createErrorResponse("Error with source or destination account. Does src contain sufficient units?");

        } catch (Exception e) {
            e.printStackTrace();
            return createErrorResponse("Error booking transfer\n" + transferJson);
        }
    }

    @GetMapping(path = "/transfer/{id}")
    public ResponseEntity<Transfer> read(@PathVariable long id) {

        var transfer = transferDao.readByPrimaryKey(id);

        return transfer != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(transfer)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of Transfers")
                        .build();
    }

    @GetMapping(path = "/transfers")
    public ResponseEntity<List<Transfer>> readAll() {
        var allTransfers = transferDao.readAll();

        return allTransfers != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(allTransfers)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of Transfers")
                        .build();
    }

    @PutMapping(path = "/transfer", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody String transferJson) {
        try {
            var objectMapper = new ObjectMapper();
            transferJson = transferJson
                    .replaceAll("\r\n", "")
                    .replaceAll("\t", "");

            var transfer = objectMapper.readValue(transferJson, Transfer.class);
            transferDao.update(transfer);

            restUtility.processHttpRequest(HttpMethod.POST, "Updated transfer " + transfer.toString(),
                    "http://localhost:8080/addEntryToRecentLog", TEXT_PLAIN_VALUE);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(transfer.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return createErrorResponse(transferJson + " Error updating transfer ");
        }
    }

    @DeleteMapping(path = "/transfer/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        var deletedTransfer = transferDao.deleteByPrimaryKey(id);

        if (deletedTransfer != null) {
            restUtility.processHttpRequest(HttpMethod.POST, "Deleted transfer " + deletedTransfer.toString(),
                    "http://localhost:8080/addEntryToRecentLog", TEXT_PLAIN_VALUE);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Transfer " + id + " deleted.");

        } else {
            return createErrorResponse("Couldn't delete transfer with id = " + id);
        }
    }

    private void logTransfer(Long transferId, Transfer newTransfer) {
        String message;

        if (transferId >= 0) {
            newTransfer.setId(transferId);
            message = "Booked transfer " + newTransfer.toString();
        } else {
            message = "Error booking transfer " + newTransfer.toString();
        }
        restUtility.processHttpRequest(HttpMethod.POST, message,
                "http://localhost:8080/addEntryToRecentLog", TEXT_PLAIN_VALUE);
    }

    private ResponseEntity<String> createErrorResponse(String message) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(message);
    }
}
