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
    private CustomerDaoRepo customerDaoRepo;

    @Test
    public void testPersistenceContextInjection(){
        assertNotNull(customerDaoRepo.getSession());
    }

    @Test
    public void testGetAllCustomers() {
        var allCustomers = customerDaoRepo.readAllCorporate();

        Assert.assertNotNull(allCustomers);
        assertTrue(allCustomers.size() > 0);
    }

    @Test
    public void testGetSingleCustomerById() {
        var customer = customerDaoRepo.read(2012L);

        Assert.assertNotNull(customer);
        assertEquals(Long.valueOf(2012L), customer.getId());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDeleteCustomer(){
        var id = 2013L;
        customerDaoRepo.delete(id);

        customerDaoRepo.read(id);
    }

/*
    @Test
    public void testGet() {
        var fund = DataModelManagerSingleton.getInstance().getCustomerDao().readCustomer(3016);
        var possibleFailureCause = "Please first check if DB was changed.";

        assertNotNull(possibleFailureCause, fund);
        assertEquals(possibleFailureCause, "IBM", fund.getName());
        assertEquals(100, fund.getTrade().getQuantity(), 0.01);
    }

    @Test
    public void testGetAvailableCustomers(){
        var allCustomers = DataModelManagerSingleton.getInstance().getCustomerDao().getAvailableCustomers();

        assertNotNull(allCustomers);
        assertTrue(allCustomers.size() > 0);
    }
*/}
