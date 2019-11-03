package org.kd.main.client.presenter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.kd.main.common.RestUtility;
import org.kd.main.common.TraderConfig;
import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Customer;
import org.kd.main.common.entities.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

@Import(TraderConfig.class)
public class TraderPresenter implements PresenterHandler {

    @Autowired
    private RestUtility restUtility;
    private final String serviceAddress = "http://localhost:8080";
    private HttpMethod requestType;
    private String requestAsString;
    private String requestUrl;

    private final Logger log = LoggerFactory.getLogger(TraderPresenter.class);

    private final TypeReference<Bank> bankTypeReference = new TypeReference<>() {
    };
    private final TypeReference<Customer> customerTypeReference = new TypeReference<>() {
    };
    private final TypeReference<List<Bank>> bankListTypeReference = new TypeReference<>() {
    };
    private final TypeReference<List<Customer>> customerListTypeReference = new TypeReference<>() {
    };
    private final TypeReference<List<Transfer>> transferListTypeReference = new TypeReference<>() {
    };

    @Override
    public List<Bank> readBanks() {
        requestUrl = serviceAddress.concat("/banks");
        requestAsString = "";

        ResponseEntity<String> response = restUtility.processHttpRequest(HttpMethod.GET, requestAsString, requestUrl, "application/json");
        if (response == null) {
            log.error("REST error. Couldn't read banks from server.");
            return new Vector<>();
        }
        restUtility.retrieveResponseBodyAndStatusCode(response);
        List<Bank> banks;
        try {
            var responseBody = Optional.ofNullable(response.getBody()).orElse("");
            banks = new ObjectMapper()
                    .readValue(responseBody
                            , bankListTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return new Vector<>();
        }

        return banks;
    }

    @Override
    public Bank readBank(long id) {
        requestType = HttpMethod.GET;
        requestUrl = serviceAddress.concat("/bank/").concat(Long.valueOf(id).toString());
        requestAsString = "";

        ResponseEntity<String> response = restUtility.processHttpRequest(HttpMethod.GET, requestAsString, requestUrl, "application/json");
        if (response == null) {
            log.error("REST error. Couldn't read bank with id={} from server.", id);
            return null;
        }
        restUtility.retrieveResponseBodyAndStatusCode(response);
        Bank bank;
        var responseBody = Optional.ofNullable(response.getBody()).orElse("");
        try {
            bank = new ObjectMapper()
                    .readValue(responseBody
                            , bankTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return bank;
    }

    @Override
    public void saveBank(Bank bank) {
        var contentType = "application/json";
        var requestUrl = serviceAddress.concat("/bank");
        requestType = HttpMethod.valueOf("PUT");

        String bankJson = new GsonBuilder().create().toJson(bank);

        var response = restUtility.processHttpRequest(requestType, bankJson, requestUrl, contentType);
        if (response == null) {
            log.error("REST error. Couldn't save bank.");
        }
        restUtility.retrieveResponseBodyAndStatusCode(response);
        if (!"200".equals(restUtility.getResponseStatusCode()))
            log.error(restUtility.getErrorResponseStatusCode() + " " + restUtility.getErrorResponseBody());

    }

    @Override
    public List<Customer> readCustomers() {
        requestType = HttpMethod.GET;
        requestUrl = serviceAddress.concat("/customers");
        requestAsString = "";

        ResponseEntity<String> response = restUtility.processHttpRequest(HttpMethod.GET, requestAsString, requestUrl, "application/json");
        if (response == null) {
            log.error("REST error. Couldn't read customers from server.");
            return new Vector<>();
        }
        restUtility.retrieveResponseBodyAndStatusCode(response);
        List<Customer> customers;
        var responseBody = Optional.ofNullable(response.getBody()).orElse("");
        try {
            customers = new ObjectMapper()
                    .readValue(responseBody
                            , customerListTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return new Vector<>();
        }

        return customers;
    }

    @Override
    public Customer readCustomer(long id) {
        requestType = HttpMethod.GET;
        requestUrl = serviceAddress.concat("/customer/").concat(Long.valueOf(id).toString());
        requestAsString = "";

        ResponseEntity<String> response = restUtility.processHttpRequest(HttpMethod.GET, requestAsString, requestUrl, "application/json");
        if (response == null) {
            log.error("REST error. Couldn't read customer with id={} from server.", id);
            return null;
        }
        restUtility.retrieveResponseBodyAndStatusCode(response);
        Customer customer;
        var responseBody = Optional.ofNullable(response.getBody()).orElse("");
        try {
            customer = new ObjectMapper()
                    .readValue(responseBody
                            , customerTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return customer;
    }

    @Override
    public void saveCustomer(Customer customer) {
        var contentType = "application/json";
        var requestUrl = serviceAddress.concat("/customer");

        Gson gsonBuilder = new GsonBuilder().create();
        String customerJson = gsonBuilder.toJson(customer);

        var response = restUtility.processHttpRequest(HttpMethod.PUT, customerJson, requestUrl, contentType);

        restUtility.retrieveResponseBodyAndStatusCode(response);
        if (!"200".equals(restUtility.getResponseStatusCode()))
            log.error(restUtility.getErrorResponseStatusCode() + " " + restUtility.getErrorResponseBody());
    }

    @Override
    public List<Transfer> readTransfers() {
        requestType = HttpMethod.GET;
        requestUrl = serviceAddress.concat("/transfers");
        requestAsString = "";

        ResponseEntity<String> response = restUtility.processHttpRequest(requestType, requestAsString, requestUrl, "application/json");
        if (response == null) {
            log.error("REST error. Couldn't read transfers from server.");
            return new Vector<>();
        }
        restUtility.retrieveResponseBodyAndStatusCode(response);
        List<Transfer> transfers;
        var responseBody = Optional.ofNullable(response.getBody()).orElse("");
        try {
            transfers = new ObjectMapper().readValue(responseBody, transferListTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return new Vector<>();
        }

        return transfers;
    }

    @Override
    public void initApplication() {
    }

    @Override
    public void saveDb() {
    }
}
