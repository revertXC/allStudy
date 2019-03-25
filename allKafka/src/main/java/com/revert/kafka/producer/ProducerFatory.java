package com.revert.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * xiecong
 */
@Component
public class ProducerFatory {


    @Autowired
    private KafkaTemplate kafkaTemplate;

    private static final String test_p = "test222";
    public ListenableFuture<SendResult> testProducer(String val){
        return kafkaTemplate.send(test_p, val);
    }

    public ListenableFuture<SendResult> nProducer(String topics ,String val){
        return kafkaTemplate.send(topics, val);
    }

    public ListenableFuture<SendResult> nProducer(String topics , String key,String val){
        return kafkaTemplate.send(topics, key, val);
    }


}
