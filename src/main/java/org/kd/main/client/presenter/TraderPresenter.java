package org.kd.main.client.presenter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Vector;

@Import(TraderConfig.class)
public class TraderPresenter implements PresenterHandler {

    @Autowired
    private RestUtility restUtility;
    private String serviceAddress = "http://localhost:8080";
    private HttpMethod requestType;
    private String requestAsString;
    private String requestUrl;
    private String responseStatusCode;
    private String responseBody;
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
    public List<Bank> loadBanks() {
        requestType = HttpMethod.valueOf("GET");
        requestUrl = serviceAddress.concat("/banks");
        requestAsString = "";

        ResponseEntity<String> response = restUtility.processHttpRequest(requestType, requestAsString, requestUrl, "application/json");
        if (response == null) {
            log.error("REST error. Couldn't read banks from server.");
            return new Vector<>();
        }
        retrieveResponseBodyAndStatusCode(response);
        List<Bank> banks;
        try {
            banks = new ObjectMapper()
                    .readValue(response.getBody()
                            , bankListTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return new Vector<>();
        }

        return banks;
    }

    @Override
    public Bank loadBank(long id) {
        requestType = HttpMethod.valueOf("GET");
        requestUrl = serviceAddress.concat("/bank/").concat(Long.valueOf(id).toString());
        requestAsString = "";

        ResponseEntity<String> response = restUtility.processHttpRequest(requestType, requestAsString, requestUrl, "application/json");
        if (response == null) {
            log.error("REST error. Couldn't read bank with id={} from server.", id);
            return null;
        }
        retrieveResponseBodyAndStatusCode(response);
        Bank bank;
        try {
            bank = new ObjectMapper()
                    .readValue(response.getBody()
                            , bankTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return bank;
    }

    @Override
    public void saveBank(Bank bank) {
        //TODO: implement
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public List<Customer> loadCustomers() {
        requestType = HttpMethod.valueOf("GET");
        requestUrl = serviceAddress.concat("/customers");
        requestAsString = "";

        ResponseEntity<String> response = restUtility.processHttpRequest(requestType, requestAsString, requestUrl, "application/json");
        if (response == null) {
            log.error("REST error. Couldn't read customers from server.");
            return new Vector<>();
        }
        retrieveResponseBodyAndStatusCode(response);
        List<Customer> customers;
        try {
            customers = new ObjectMapper()
                    .readValue(response.getBody()
                            , customerListTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return new Vector<>();
        }

        return customers;
    }

    @Override
    public Customer loadCustomer(long id) {
        requestType = HttpMethod.valueOf("GET");
        requestUrl = serviceAddress.concat("/customer/").concat(Long.valueOf(id).toString());
        requestAsString = "";

        ResponseEntity<String> response = restUtility.processHttpRequest(requestType, requestAsString, requestUrl, "application/json");
        if (response == null) {
            log.error("REST error. Couldn't read customer with id={} from server.", id);
            return null;
        }
        retrieveResponseBodyAndStatusCode(response);
        Customer customer;
        try {
            customer = new ObjectMapper()
                    .readValue(response.getBody()
                            , customerTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return customer;
    }

    @Override
    public void saveCustomer(Customer customer) {
        //TODO: implement
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public List<Transfer> loadTransfers() {
        requestType = HttpMethod.valueOf("GET");
        requestUrl = serviceAddress.concat("/transfers");
        requestAsString = "";

        ResponseEntity<String> response = restUtility.processHttpRequest(requestType, requestAsString, requestUrl, "application/json");
        if (response == null) {
            log.error("REST error. Couldn't read transfers from server.");
            return new Vector<>();
        }
        retrieveResponseBodyAndStatusCode(response);
        List<Transfer> transfers;
        try {
            transfers = new ObjectMapper()
                    .readValue(response.getBody()
                            , transferListTypeReference);
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

    private void retrieveResponseBodyAndStatusCode(ResponseEntity<String> response) {
        if (response != null) {
            int responseStatusCodeNumber = response.getStatusCodeValue();
            responseStatusCode = "" + responseStatusCodeNumber;
            responseBody = response.getBody();
            log.info("Status code: " + responseStatusCode);
        } else {
            responseStatusCode = restUtility.getErrorResponseStatusCode();
            responseBody = restUtility.getErrorResponseBody();
            log.info("Status code: " + responseStatusCode);
        }
    }
}
