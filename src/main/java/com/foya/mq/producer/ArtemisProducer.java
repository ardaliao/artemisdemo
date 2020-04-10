package com.foya.mq.producer;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class ArtemisProducer {

    @Autowired
    JmsTemplate jmsTemplateAny;

    @Autowired
    JmsTemplate jmsTemplateMulti;

    /*
    @Value("${jms.queue.destination}")
    String destinationQueue;
*/
    public void send(String msg){

        jmsTemplateAny.setPubSubDomain(false);

        jmsTemplateAny.convertAndSend(  "VirtualTopic.any",msg);

    }

    public void sendMulti(String msg){
        jmsTemplateMulti.setPubSubDomain(true);

        jmsTemplateMulti.convertAndSend( new ActiveMQTopic("VirtualTopic.multi"),msg);
    }


}
