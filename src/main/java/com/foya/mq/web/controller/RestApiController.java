package com.foya.mq.web.controller;

import com.foya.mq.producer.ArtemisProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    @Autowired
    ArtemisProducer producer;

    @GetMapping(value="/send")
    public String produce(@RequestParam("msg")String msg){

        producer.send(msg);
        return "Done";
    }


    @GetMapping(value="/sendMulti")
    public String sendMulti(@RequestParam("msg")String msg){

        producer.sendMulti(msg);
        return "DoneMulti";
    }


}
