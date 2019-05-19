package org.kd.main.server.rest.config;

import org.kd.main.client.presenter.RestUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig {

    @Bean
    RestUtility restUtility() {
        return new RestUtility();
    }
}
