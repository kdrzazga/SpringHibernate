package org.kd.main.client.presenter;

import com.fasterxml.jackson.core.type.TypeReference;
import org.kd.main.common.TraderConfig;
import org.kd.main.common.entities.Bank;
import org.kd.main.common.entities.Fund;
import org.kd.main.common.entities.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

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


    Logger log = LoggerFactory.getLogger(TraderPresenter.class);

    @Override
    public List<Bank> loadBanks() {
        requestType = HttpMethod.valueOf("GET");
        requestUrl = serviceAddress.concat("/countries");
        requestAsString = "";

        ResponseEntity<String> response = restUtility.processHttpRequest(requestType, requestAsString, requestUrl, "application/json");
        retrieveResponseBodyAndStatusCode(response);
        var mapper = new ObjectMapper();
        List<Bank> banks = null;
        try {
            banks = mapper.readValue(response.getBody(), new TypeReference<List<Bank>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return banks;
    }

    @Override
    public Bank loadBank(long id) {
        //TODO: implement
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public void saveBank(Bank bank) {
        //TODO: implement
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public List<Fund> loadFunds() {
        //TODO: implement
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public Fund loadFund(long id) {
        //TODO: implement
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public void saveFund(Fund fund) {
        //TODO: implement
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public List<Transfer> loadTransfers() {
        //TODO: implement
        throw new RuntimeException("not implemented yet");
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
