package org.kd.main.server;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.kd.main.common", "org.kd.main.server"})
public class TraderServer {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(TraderServer.class, args);
        writeInfo();
    }

    public static void stop() {
        context.stop();
        context.close();
    }

    private static void writeInfo() {
        LoggerFactory.getLogger(TraderServer.class).info("\nH2 database link: http://localhost:8080/h2-console");
        LoggerFactory.getLogger(TraderServer.class).info("Make sure h2 console is enabled in application.properties");
    }
}
