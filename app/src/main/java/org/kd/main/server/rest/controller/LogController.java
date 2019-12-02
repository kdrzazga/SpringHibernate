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

    private final LogDaoRepo countryDao;

    @Autowired
    public LogController(LogDaoRepo countryDao) {
        this.countryDao = countryDao;
    }

    @GetMapping(path = "/log/{id}", produces = "application/json")
    public ResponseEntity<Log> readLog(@PathVariable long id) {
        var city = countryDao.read(id);

        return city != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(city)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Couldn't read " + Log.class.getSimpleName() + " with id = " + id)
                        .build();
    }

    @GetMapping(path = "/logs")
    public ResponseEntity<List<Log>> readBanks() {
        var allCountries = countryDao.readAll();

        return allCountries != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(allCountries)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of " + Log.class.getSimpleName())
                        .build();
    }
}
