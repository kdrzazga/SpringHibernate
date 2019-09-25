package org.kd.main.server.model.data.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.main.common.entities.Customer;
import org.kd.main.server.TraderServer;
import org.kd.main.common.entities.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(classes = {TraderServer.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class BankDaoRepoTest {

    @Autowired
    private BankDaoRepo bankDaoRepo;

    @Test
    public void testGetAllBanks() {
        var allParties = bankDaoRepo.readAll();

        Assert.assertNotNull(allParties);
        assertTrue(allParties.size() > 0);
    }

    @Test
    public void testGetSingleBankById() {
        var bank = bankDaoRepo.read(1012L);

        Assert.assertNotNull(bank);
        assertEquals(1012L, bank.getId());
    }

    @Test
    public void testGetSingleBankByName() {
        var bank = bankDaoRepo.read("BABA");

        Assert.assertNotNull(bank);
        assertEquals("Alibaba Group Holding", bank.getName());
    }

    @Test
    public void testGetAssociatedFund() {
        List<Customer> associatedCustomers = bankDaoRepo.readAssociatedCustomers(1012L);

        Assert.assertNotNull(associatedCustomers);
        assertTrue(associatedCustomers.size() > 0);
    }

    @Test
    public void testInsert(){
        var bankId = bankDaoRepo.insert(new Bank("Test Bank", "TEST"));
        assertEquals(bankId, bankDaoRepo.read("TEST").getId());
    }

    @Test
    public void testBankUpdate(){
        var newBankName = "NEW TEST NAME";
        var bank = bankDaoRepo.read(1012L);
        bank.setName(newBankName);
        bankDaoRepo.update(bank);

        var readBank = bankDaoRepo.read(1012L);
        assertEquals(newBankName, readBank.getName());
    }

}
