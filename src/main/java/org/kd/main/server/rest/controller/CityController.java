package org.kd.main.server.rest.controller;

import org.kd.main.common.entities.City;
import org.kd.main.server.model.data.dao.CityDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private CityDaoRepo cityDao;

    @GetMapping(path = "/city/{id}", produces = "application/json")
    public ResponseEntity<City> readCity(@PathVariable long id) {
        var city = cityDao.read(id);

        return city != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(city)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Couldn't read " + City.class.getSimpleName() + "  with id = " + id)
                        .build();
    }

    @GetMapping(path = "/cities")
    public ResponseEntity<List<City>> readCities() {
        var allBanks = cityDao.readAll();

        return allBanks != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(allBanks)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of " + City.class.getSimpleName())
                        .build();
    }
}
