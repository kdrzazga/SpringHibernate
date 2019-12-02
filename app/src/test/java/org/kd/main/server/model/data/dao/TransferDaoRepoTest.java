package org.kd.main.server.model.data.dao;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.kd.main.server.TraderServer;
import org.kd.main.common.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.function.Predicate;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {TraderServer.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class TransferDaoRepoTest {

    @Autowired
    private TransferDaoRepo transferDaoRepo;

    @Autowired
    private CustomerDaoRepo customerDaoRepo;

    @Test
    @Order(value = 1)
    public void testBookInternalTransfer() {
        var srcCustomerId = 2002L;
        var commonBankId = customerDaoRepo.read(srcCustomerId).getBank_id();

        checkBookingTransfer(srcCustomerId, customer -> Objects.equals(customer.getBank_id(), commonBankId));
    }

    @Ignore("functionality not implemented yet")
    @Test
    public void testBookExternalTransfer() {
        var srcCustomerId = 2011L;
        var commonBankId = customerDaoRepo.read(srcCustomerId).getBank_id();

        checkBookingTransfer(srcCustomerId, customer -> !Objects.equals(customer.getBank_id(), commonBankId));
    }

    @Test
    @Order(value = 2)
    public void testReadByPrimaryKey() {
        assertEquals(Long.valueOf(3002), transferDaoRepo.readByPrimaryKey(3002L).getId());
        assertEquals(Long.valueOf(3003), transferDaoRepo.readByPrimaryKey(3003L).getId());
        assertEquals(Long.valueOf(3005), transferDaoRepo.readByPrimaryKey(3005L).getId());
    }

    @Test
    @Ignore
    @Order(value = 3)
    public void testReadForParticularFund() {
        var tradeForFund2002 = transferDaoRepo.readByFundId(2002);
        Assert.assertNotNull(tradeForFund2002);
        assertEquals(3, tradeForFund2002.size());
    }

    @Test
    @Order(value = 4)
    public void testReadAll() {
        var transacts = transferDaoRepo.readAll();

        Assert.assertNotNull(transacts);
        assertThat(transacts, hasSize(greaterThan(0)));
        assertThat(transacts, hasSize(greaterThanOrEqualTo(5)));
        assertThat(transacts, hasSize(lessThanOrEqualTo(6)));
    }

    @Test
    @Order(value = 5)
    public void testDeleteTransferByPrimaryKey() {
        var id = 3006L;
        transferDaoRepo.deleteByPrimaryKey(id);
        Assert.assertNull(transferDaoRepo.readByPrimaryKey(id));
        //transferDaoRepo.book(2003L, 2004L, 30.02f);//books transact again, but id will change
    }

    private void checkBookingTransfer(long srcFundId, Predicate<Customer> partyIdComparisonPredicate) {

        var destCustomer = customerDaoRepo.readAll()
                .stream()
                .filter(partyIdComparisonPredicate)
                .findFirst();

        if (!destCustomer.isPresent())
            fail("Wrong test data. Cannot book Transfer. Only one customer with appropriate bank id ");

        final int errorCode = -1;
        final long newTransferId =  transferDaoRepo.book(srcFundId, destCustomer.get().getId(), 0.5f);

        assertNotEquals(errorCode, newTransferId);

        removeBookedTransfer(newTransferId);
    }

    private void removeBookedTransfer(long id) {
        transferDaoRepo.deleteByPrimaryKey(id);
    }

}
