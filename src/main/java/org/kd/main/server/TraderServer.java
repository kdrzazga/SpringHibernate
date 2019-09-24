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
        start();
        writeInfo();
    }

    public static void start() {
        if (context == null) {
            context = SpringApplication.run(TraderServer.class);
        }
    }

    public static void stop() {
        context.stop();
        context.close();
    }

    private static void writeInfo() {
        LoggerFactory.getLogger(TraderServer.class).info("\nH2 database link: http://localhost:8080/h2-console");
        LoggerFactory.getLogger(TraderServer.class).info("Make sure h2 console is enabled in application.properties");
        LoggerFactory.getLogger(TraderServer.class).info("\n\n\nSERVER STARTED\n\n");
    }
}
