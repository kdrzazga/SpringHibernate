package org.kd.main.server.rest.controller;

import org.kd.main.common.entities.RealEstate;
import org.kd.main.server.model.data.dao.RealEstateDaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RealEstateController {

    private final RealEstateDaoRepo realEstateDao;

    @Autowired
    public RealEstateController(RealEstateDaoRepo realEstateDao) {
        this.realEstateDao = realEstateDao;
    }

    @GetMapping(path = "/realestate/{id}", produces = "application/json")
    public ResponseEntity<RealEstate> readRealEstate(@PathVariable long id) {
        var city = realEstateDao.read(id);

        return city != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(city)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Couldn't read " + RealEstate.class.getSimpleName() + " with id = " + id)
                        .build();
    }

    @GetMapping(path = "/realestates")
    public ResponseEntity<List<RealEstate>> readRealEstates() {
        var allBanks = realEstateDao.readAll();

        return allBanks != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(allBanks)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of " + RealEstate.class.getSimpleName())
                        .build();
    }
}
