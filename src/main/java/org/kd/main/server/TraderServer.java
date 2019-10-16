package org.kd.main.server;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = {"org.kd.main.common", "org.kd.main.server"})
@PropertySource("classpath:application.properties")
public class TraderServer {

    private static final TraderServer instance = new TraderServer();
    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        getInstance().start();
        getInstance().writeInfo();
    }

    public void start() {
        if (context == null) {
            context = SpringApplication.run(TraderServer.class);
        }
    }

    public void stop() {
        context.stop();
        context.close();
    }

    private void writeInfo() {
        LoggerFactory.getLogger(TraderServer.class).info("\nH2 database link: http://localhost:" + context.getBean("server_port", String.class) + "/h2-console");
        LoggerFactory.getLogger(TraderServer.class).info("Make sure h2 console is enabled in application.properties");
        LoggerFactory.getLogger(TraderServer.class).info("\n\n\nSERVER STARTED\n\n");
    }

    public static TraderServer getInstance() {
        return instance;
    }
}
