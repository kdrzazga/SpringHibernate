package org.kd.main.client.presenter.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestClientConfig {

    @Bean
    public WireMockServer wireMockServer() {
        return new WireMockServer();
    }
}
