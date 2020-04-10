package com.foya.mq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ArtemisConsumer {

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
    public void receiveAny1(String msg) {
        System.out.println("receiveAny1 Got Message: " + msg);
    }

    @JmsListener(destination = "VirtualTopic.any",containerFactory = "queueListenerFactory")
    public void receiveAny2(String msg) {
        System.out.println("receiveAny2 Got Message: " + msg);
    }

    @JmsListener( destination = "VirtualTopic.multi",containerFactory = "topicListenerFactory")
    public void receiveMulti1(String msg) {
        System.out.println("receiveMulti3 Got Message: " + msg);
    }

    @JmsListener(destination = "VirtualTopic.multi",containerFactory = "topicListenerFactory")
    public void receiveMulti2(String msg) {
        System.out.println("receiveMulti4 Got Message: " + msg);
    }



}