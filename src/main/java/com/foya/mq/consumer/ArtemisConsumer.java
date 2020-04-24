package com.foya.mq.consumer;

import com.foya.mq.bean.MessageObj;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ArtemisConsumer {
    Logger logger = LoggerFactory.getLogger(ArtemisConsumer.class);
    /*
    @JmsListener(destination = "${jms.queue.destination}")
    public void receive1(String msg) {
        System.out.println("receive1 Got Message: " + msg);
    }


    @JmsListener(destination = "${jms.queue.destination}")
    public void receive2(String msg) {
        System.out.println("receive2 Got Message: " + msg);
    }
*/
    @JmsListener(destination = "VirtualTopic.any",containerFactory = "queueListenerFactory")
    public void receiveAny1(MessageObj msg) {
        logger.debug("receiveAny1 Got Message: " + msg);
        System.out.println("receiveAny1 Got Message: " + msg);
    }

    @JmsListener(destination = "VirtualTopic.any",containerFactory = "queueListenerFactory")
    public void receiveAny2(MessageObj msg) {
        logger.debug("receiveAny2 Got Message: " + msg);
        System.out.println("receiveAny2 Got Message: " + msg);
    }

    @JmsListener( destination = "VirtualTopic.multi",containerFactory = "topicListenerFactory")
    public void receiveMulti1(MessageObj msg) {
        System.out.println("receiveMulti3 Got Message: " + msg);
    }

    @JmsListener(destination = "VirtualTopic.multi",containerFactory = "topicListenerFactory")
    public void receiveMulti2(MessageObj msg) {


        System.out.println("receiveMulti4 Got Message: " +msg+",map=" +StringUtils.join(msg.getParams())

        );

    }

    @JmsListener(destination = "VirtualTopic.*",containerFactory = "topicListenerFactory")
    public void receiveGlobal(MessageObj msg) {
        System.out.println("receiveGlobal Got Message: " + msg);
    }


}