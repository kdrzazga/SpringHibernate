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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
class RealEstateController {

    private final RealEstateDaoRepo realEstateDao;

    @Autowired
    public RealEstateController(RealEstateDaoRepo realEstateDao) {
        this.realEstateDao = realEstateDao;
    }

    @GetMapping(path = "/realestate/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RealEstate> read(@PathVariable long id) {
        var realEstate = realEstateDao.read(id);

        return realEstate != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(realEstate)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Couldn't read " + RealEstate.class.getSimpleName() + " with id = " + id)
                        .build();
    }

    @GetMapping(path = "/realestates")
    public ResponseEntity<List<RealEstate>> readAll() {
        var realEstates = realEstateDao.readAll();

        return realEstates != null ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(realEstates)
                :
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header("message", "Error reading list of " + RealEstate.class.getSimpleName())
                        .build();
    }
}
