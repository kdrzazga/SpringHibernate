package org.kd.main.client.presenter;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd.main.client.presenter.config.TestClientConfig;
import org.kd.main.common.TraderConfig;
import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Customer;
import org.kd.main.common.entities.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TraderConfig.class, TestClientConfig.class})
@PropertySource("classpath:application.properties")
public class PresenterTest {

    @Value("${port:8080}")
    private String port;

    private final Bank bank = new Bank(1, "Test Bank", "TST");
    private final Customer customer = new Customer("IK", "Ian Kovalsky", 1, 1);
    private final Transfer transfer = new Transfer(1, 1, 2, true);

    @Autowired
    private WireMockServer wireMockServer;

    @Autowired
    PresenterHandler presenterHandler;

    @Before
    public void setup() {
        wireMockServer.start();
        configureFor("localhost", Integer.parseInt(port));

        Gson gsonBuilder = new GsonBuilder().create();

        String banksAsJson = gsonBuilder.toJson(List.of(bank));
        String customersAsJson = gsonBuilder.toJson(List.of(customer));
        String transfersAsJson = gsonBuilder.toJson(List.of(transfer));

        createStubForGet(banksAsJson, "/banks");
        createStubForGet(customersAsJson, "/customers");
        createStubForGet(transfersAsJson, "/transfers");
    }

    public void createStubForGet(String banksAsJson, String endpoint) {
        var response = aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", "application/json")
                .withBody(banksAsJson);

        stubFor(get(urlEqualTo(endpoint))
                .willReturn(response));
    }

    @Test
    public void testLoadBanks() {
        var banks = presenterHandler.loadBanks();
        assertNotNull(banks);
        assertThat(banks, hasSize(greaterThan(0)));
        assertEquals(bank, banks.get(0));
    }

    @Test
    public void testLoadCustomers() {
        var customers = presenterHandler.loadCustomers();
        assertNotNull(customers);
        assertThat(customers, hasSize(greaterThan(0)));
        assertEquals(customer, customers.get(0));
    }

    @Test
    public void testLoadTransfers() {
        var transfers = presenterHandler.loadTransfers();
        assertNotNull(transfers);
        assertThat(transfers, hasSize(greaterThan(0)));
        assertEquals(transfer, transfers.get(0));
    }
}
