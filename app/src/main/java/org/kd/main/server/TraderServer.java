package org.kd.main.server;

import org.kd.main.server.messaging.service.CurrencyMessageListener;
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
    private CurrencyMessageListener listener;

    public static void main(String[] args) {
        getInstance().start();
        getInstance().writeInfo();
       /* getInstance().createMessageListener();*/
        /*getInstance().startMessageListener();
        getInstance().startMessageListenerThread();*/
    }

    public void start() {
        if (context == null) {
            context = SpringApplication.run(TraderServer.class);
        }
    }

    public void startMessageListener() {
        listener.start();
    }

    private void createMessageListener() {
        listener = context.getBean(CurrencyMessageListener.class);
    }

    private void startMessageListenerThread() {
        listener.run();
    }

    public void stopMessageListener() {
        listener.stop();
    }

    public void stop() {
        stopMessageListener();
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
