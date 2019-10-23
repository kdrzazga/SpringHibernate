package org.kd.main.server.messaging.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.kd.main.common.messaging.SpringJmsConsumer;
import org.kd.main.server.messaging.service.CurrencyMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import java.net.URI;

@Configuration
public class JmsConfig {

    private ConnectionFactory connectionFactory() {
        var factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://localhost:61616");
        return factory;
    }

    private ActiveMQQueue messageDestination() {
        return new ActiveMQQueue("messageQueue1");
    }

    private JmsTemplate jmsTemplate() {
        var template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setReceiveTimeout(10000);
        return template;
    }

    @Bean
    public SpringJmsConsumer springJmsConsumer() {
        var consumer = new SpringJmsConsumer();
        consumer.setDestination(messageDestination());
        consumer.setJmsTemplate(jmsTemplate());
        return consumer;
    }

    @Bean
    public BrokerService brokerService() {
        try {
            return BrokerFactory.createBroker(new URI(
                    "broker:(tcp://localhost:61616)"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean(name = "listener")
    public CurrencyMessageListener messageListener(){
        return new CurrencyMessageListener();
    }
}
