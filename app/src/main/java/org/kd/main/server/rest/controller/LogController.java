package org.kd.main.server.rest.controller;

import org.kd.main.common.entities.Log;
import org.kd.main.server.model.data.dao.LogDaoRepo;
import org.kd.main.server.model.operation.LogOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
class LogController {

    private final LogDaoRepo logDaoRepo;

    private final LogOperation logOperation;

    @Autowired
    private LogController(LogDaoRepo logDaoRepo, LogOperation logOperation) {
        this.logDaoRepo = logDaoRepo;
        this.logOperation = logOperation;
    }

    @PostMapping(path = "/log/{filepath}")
    private ResponseEntity<String> create(@PathVariable String filepath) {
        var file = logOperation.createEmptyLogFile(filepath);

        var timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
        var newId = logDaoRepo.create(new Log(timestamp, file));

        return "".equals(file) && newId != null
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.status(HttpStatus.OK)
                .body(file);
    }

    @PostMapping(path = "/addEntryToRecentLog", consumes = TEXT_PLAIN_VALUE, produces = TEXT_PLAIN_VALUE)
    public ResponseEntity<String> addEntryToRecentLog(@RequestBody String message) {
        var recentLog = findOrCreateRecentLog();

        return recentLog.addMessage(message)
                ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Written\n" + message + "\n to file:\n" + recentLog.getFilepath())
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("ERROR writing\n" + message + "\n to file:\n" + recentLog.getFilepath()
                                + "\nCheck if log file exists on disk.");
    }

    @GetMapping(path = "/recentLog", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Log> findRecentLog() {

        Optional<Log> recentLog = logDaoRepo.findRecentLog();

        return recentLog.isPresent()
                ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(recentLog.get())
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Log not found")
                        .build();
    }

    @GetMapping(path = "/log/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Log> read(@PathVariable long id) {
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
    public ResponseEntity<List<Log>> readAll() {
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

    private Log findOrCreateRecentLog() {
        Optional<Log> recentLog = logDaoRepo.findRecentLog();

        if (recentLog.isEmpty()) {
            Logger.getLogger(LogController.class.getSimpleName()).warning("Recent log not found. Atempting to create a new one.\n");
            var response = create("newlog");

            Logger.getLogger(LogController.class.getSimpleName()).warning(response.getBody());
            recentLog = logDaoRepo.findRecentLog();
            //TODO
        }
        return recentLog.get();
    }

}
