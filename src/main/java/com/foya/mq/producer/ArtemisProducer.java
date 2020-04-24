package com.foya.mq.producer;

import com.foya.mq.bean.MessageObj;
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
import java.util.HashMap;
import java.util.Map;

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
    public void sendAny(String msg){

        jmsTemplateAny.setPubSubDomain(false);

        MessageObj obj = new MessageObj();
        obj.setId(String.valueOf(System.currentTimeMillis()));
        obj.setName("From Any");
        obj.setUrl("localhost");
        Map<java.lang.String,Object> map =new HashMap();
        map.put("a","a");
        map.put("msg",msg);
        map.put("o",new String[]{"o1","02"});
        obj.setParams(map);
        jmsTemplateAny.convertAndSend(  "VirtualTopic.any",obj);

    }

    public void sendMulti(String msg){
        jmsTemplateMulti.setPubSubDomain(true);
        MessageObj obj = new MessageObj();
        obj.setId(String.valueOf(System.currentTimeMillis()));
        obj.setName("From Multi");
        obj.setUrl("localhost");
        Map<java.lang.String,Object> map =new HashMap();
        map.put("a","b");
        map.put("o",new String[]{"c1","c2"});
        map.put("msg",msg);
        obj.setParams(map);
        jmsTemplateMulti.convertAndSend( new ActiveMQTopic("VirtualTopic.multi"),obj);
    }

    public void send(String msg){

        jmsTemplateMulti.setPubSubDomain(true);

        MessageObj obj = new MessageObj();
        obj.setId(String.valueOf(System.currentTimeMillis()));
        obj.setName("From send");
        obj.setUrl("localhost");
        Map<java.lang.String,Object> map =new HashMap();
        map.put("a","a");
        map.put("msg",msg);
        map.put("o",new String[]{"o1","02"});
        obj.setParams(map);
        jmsTemplateMulti.convertAndSend(  "VirtualTopic",obj);

    }
}
