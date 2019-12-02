package org.kd.main.server.rest.controller;

import org.kd.main.common.entities.Log;
import org.kd.main.server.model.data.dao.LogDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogController {

    private final LogDaoRepo logDaoRepo;

    @Autowired
    public LogController(LogDaoRepo logDaoRepo) {
        this.logDaoRepo = logDaoRepo;
    }

    @GetMapping(path = "/log/{id}", produces = "application/json")
    public ResponseEntity<Log> readLog(@PathVariable long id) {
        var log = logDaoRepo.read(id);

        return log != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(log)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Couldn't read " + Log.class.getSimpleName() + " with id = " + id)
                        .build();
    }

    @GetMapping(path = "/logs")
    public ResponseEntity<List<Log>> readLogs() {
        var logList = logDaoRepo.readAll();

        return logList != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(logList)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of " + Log.class.getSimpleName())
                        .build();
    }
}
