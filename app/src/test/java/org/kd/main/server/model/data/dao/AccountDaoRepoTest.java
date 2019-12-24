package org.kd.main.server.model.data.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.kd.main.server.TraderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {TraderServer.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountDaoRepoTest {

    @Autowired
    private AccountDaoRepo accountDaoRepo;

    @Test
    public void testPersistenceContextInjection(){
        assertNotNull(accountDaoRepo.getSession());
    }

    @Test
    public void testReadAllCorporate() {
        var allCustomers = accountDaoRepo.readAllCorporate();

        Assert.assertNotNull(allCustomers);
        assertTrue(allCustomers.size() > 0);
    }

    @Test
    public void testGetSingleAccountById() {
        var customer = accountDaoRepo.read(2012L);

        Assert.assertNotNull(customer);
        assertEquals(Long.valueOf(2012L), customer.getId());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDeleteCustomer(){
        var id = 2013L;
        accountDaoRepo.delete(id);

        accountDaoRepo.read(id);
    }
}
