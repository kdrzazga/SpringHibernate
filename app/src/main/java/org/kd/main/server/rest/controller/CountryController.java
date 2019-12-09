package org.kd.main.server.rest.controller;

import org.kd.main.common.entities.Country;
import org.kd.main.server.model.data.dao.CountryDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
class CountryController {

    private final CountryDaoRepo countryDao;

    @Autowired
    public CountryController(CountryDaoRepo countryDao) {
        this.countryDao = countryDao;
    }

    @GetMapping(path = "/country/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> read(@PathVariable long id) {
        var country = countryDao.read(id);

        return country != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(country)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Couldn't read " + Country.class.getSimpleName() + " with id = " + id)
                        .build();
    }

    @GetMapping(path = "/countries")
    public ResponseEntity<List<Country>> readAll() {
        var allCountries = countryDao.readAll();

        return allCountries != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(allCountries)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of " + Country.class.getSimpleName())
                        .build();
    }
}
