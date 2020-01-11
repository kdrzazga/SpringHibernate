package org.kd.main.server.model.data.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.kd.main.common.entities.Account;
import org.kd.main.common.entities.Bank;
import org.kd.main.server.TraderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {TraderServer.class})
//@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BankDaoRepoTest {

    @Autowired
    private BankDaoRepo bankDaoRepo;

    @Test
    public void testCreate() {
        Long bankId = bankDaoRepo.create(new Bank("Test Bank", "TEST"));
        assertEquals(bankId, bankDaoRepo.read("TEST").getId());
    }

    @Test
    public void testGetAllBanks() {
        var allBanks = bankDaoRepo.readAll();

        Assert.assertNotNull(allBanks);
        assertTrue(allBanks.size() > 0);
    }

    @Test
    public void testGetSingleBankById() {
        var id = Long.valueOf(1012);
        var bank = bankDaoRepo.read(id);

        Assert.assertNotNull(bank);
        assertEquals(id, bank.getId());
    }

    @Test
    public void testGetSingleBankByName() {
        var bank = bankDaoRepo.read("BABA");

        Assert.assertNotNull(bank);
        assertEquals("Alibaba Group Holding", bank.getName());
    }

    @Test
    public void testGetAssociatedAccounts() {
        List<Account> associatedAccounts = bankDaoRepo.readAssociatedAccounts(1012L);

        Assert.assertNotNull(associatedAccounts);
        assertThat(associatedAccounts.size(), greaterThan(0));
        assertThat(associatedAccounts, hasSize(4));
    }

    @Test
    public void testBankUpdate() {
        var newBankName = "NEW TEST NAME";
        var bank = bankDaoRepo.read(1012L);
        bank.setName(newBankName);
        bankDaoRepo.update(bank);

        var readBank = bankDaoRepo.read(1012L);
        assertEquals(newBankName, readBank.getName());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDeleteWithFkNulling() {
        var id = 1011L;
        var associatedAccounts = bankDaoRepo.readAssociatedAccounts(id);

        assertThat("Please select a bank with Accounts for this test", associatedAccounts, hasSize(greaterThan(0)));

        assertTrue(bankDaoRepo.deleteWithFkNulling(id));

        var associatedAccountsAfterDelete = bankDaoRepo.readAssociatedAccounts(id);

        assertThat(associatedAccounts, hasSize(greaterThan(associatedAccountsAfterDelete.size())));
        assertThat(associatedAccountsAfterDelete, hasSize(0));

        bankDaoRepo.read(id);//Exception expected
    }
}
