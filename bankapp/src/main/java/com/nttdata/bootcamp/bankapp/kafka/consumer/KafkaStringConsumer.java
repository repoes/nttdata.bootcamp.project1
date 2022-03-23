package com.nttdata.bootcamp.bankapp.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaStringConsumer {

    Logger logger = LoggerFactory.getLogger(KafkaStringConsumer.class);

    @KafkaListener(topics = "quickstart-events" , groupId = "group_id")
    public void consume(String message) {
        //logger.info("Consuming Message {}", message);
    }

}