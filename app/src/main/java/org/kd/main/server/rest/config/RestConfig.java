package org.kd.main.server.rest.config;

import org.kd.main.common.RestUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RestConfig {

    @Bean
    RestUtility restUtility() {
        return new RestUtility();
    }
}
