package org.kd.main.server.rest.controller;

import org.kd.main.server.model.data.dao.BankDaoRepo;
import org.kd.main.server.model.data.dao.FundDaoRepo;
import org.kd.main.server.model.data.dao.TransferDaoRepo;
import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Fund;
import org.kd.main.common.entities.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private FundDaoRepo customerDao;

    @Autowired
    private BankDaoRepo bankDao;

    @Autowired
    private TransferDaoRepo transferDao;

    @GetMapping(path = "/customers")
    public List<Fund> readCustomers() {
        return customerDao.getAllFunds();
    }

    @GetMapping(path = "/banks")
    public List<Bank> readBanks() {
        return bankDao.getAllBanks();
    }

    @GetMapping(path = "/transfers")
    public List<Transfer> readTransfers() {
        return transferDao.getAllTransfers();
    }
}
