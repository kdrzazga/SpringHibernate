package org.kd.main.common;

import org.kd.main.client.presenter.PresenterHandler;
import org.kd.main.client.presenter.TraderPresenter;
import org.kd.main.common.model.data.DataService;
import org.kd.main.server.model.data.DataServiceImpl;
import org.kd.main.server.model.data.dao.BankDaoRepo;
import org.kd.main.server.model.data.dao.FundDaoRepo;
import org.kd.main.server.model.data.dao.TransferDaoRepo;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EntityScan(basePackages = "org.kd.main.common.entities")
public class TraderConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

   // @Bean
    PresenterHandler presenterHandler(){
        return new TraderPresenter();
    }

    //@Bean
    public DataService dataService() {
        var customerDaoRepo = new FundDaoRepo();
        var bankDaoRepo = new BankDaoRepo();
        var transferDaoRepo = new TransferDaoRepo();
        return new DataServiceImpl(customerDaoRepo, bankDaoRepo, transferDaoRepo);
    }
}
