package org.kd.main.server.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kd.main.common.entities.Transfer;
import org.kd.main.server.model.data.dao.TransferDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransferController {

    @Autowired
    private TransferDaoRepo transferDao;

    @PostMapping(path = "/transfer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> bookTransfer(@RequestBody String transferJson) {
        try {
            var objectMapper = new ObjectMapper();
            transferJson = transferJson
                    .replaceAll("\r\n", "")
                    .replaceAll("\t", "");

            var transfer = objectMapper.readValue(transferJson, Transfer.class);
            transferDao.book(transfer.getSrc_fund_id(), transfer.getDest_fund_id(), transfer.getUnits());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(transfer.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error booking transfer " + transferJson);
        }
    }

    @GetMapping(path = "/transfer/{id}")
    public ResponseEntity<Transfer> readTransfer(@PathVariable long id) {

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
    public ResponseEntity<List<Transfer>> readTransfers() {
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

    @PutMapping(path = "/transfer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateTransfer(@RequestBody String transferJson) {
        try {
            var objectMapper = new ObjectMapper();
            transferJson = transferJson
                    .replaceAll("\r\n", "")
                    .replaceAll("\t", "");

            var transfer = objectMapper.readValue(transferJson, Transfer.class);
            transferDao.update(transfer);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(transfer.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating transfer " + transferJson);
        }
    }

    @DeleteMapping(path = "/transfer/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return (transferDao.deleteByPrimaryKey(id) != null)
                ? ResponseEntity
                .status(HttpStatus.OK)
                .body("Transfer " + id + " deleted.")

                : ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Couldn't delete transfer with id = " + id);
    }
}
