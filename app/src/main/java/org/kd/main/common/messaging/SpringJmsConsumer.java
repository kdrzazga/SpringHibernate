package org.kd.main.common.messaging;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.List;
import java.util.Vector;

public class SpringJmsConsumer {
    private JmsTemplate jmsTemplate;
    private Destination destination;

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String receiveMessage() throws JMSException {
        var textMessage = (TextMessage) jmsTemplate.receive(destination);
        return (textMessage != null) ? textMessage.getText() : "";

    }

    public List<String> receiveAllMessages() throws JMSException {
        var msgs = new Vector<String>();
        String msg;
        do {
            msg = receiveMessage();
            if (!msg.equals("")) msgs.add(msg);
        }
        while (!msg.equals(""));
        return msgs;
    }
}
