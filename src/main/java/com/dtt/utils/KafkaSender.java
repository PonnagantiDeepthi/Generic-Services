package com.dtt.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.dtt.requestDTO.LogModelDTO;


@Service
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${com.dt.kafka.topic.central}")
    private String centralTopic;

    @Value("${com.dt.kafka.topic.oblog}")
    private String obLogTopic;

    public void send(LogModelDTO logmodel) {
        System.out.println("Sending to topic: " + centralTopic);
        kafkaTemplate.send(centralTopic, logmodel);
    }

    public void sendString(String logmodel) {
        System.out.println("Sending STRING to topic: " + obLogTopic);
        kafkaTemplate.send(obLogTopic, logmodel);
    }
}
