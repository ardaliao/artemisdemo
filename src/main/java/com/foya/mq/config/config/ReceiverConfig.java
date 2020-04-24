package com.foya.mq.config.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.SimpleMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import java.util.concurrent.Executors;

@Configuration
public class ReceiverConfig {
    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory,
                                                               DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // the configurer will use PubSubDomain from application.properties if defined or false if not
        //so setting it on the factory level need to be set after this
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        factory.setTaskExecutor(Executors.newFixedThreadPool(6));
        factory.setConcurrency("6");
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory,
                                                               DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // the configurer will use PubSubDomain from application.properties if defined or false if not
        //so setting it on the factory level need to be set after this
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(false);
        factory.setTaskExecutor(Executors.newFixedThreadPool(6));
        factory.setConcurrency("6");
        return factory;
    }


}
