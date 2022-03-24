package com.nttdata.bootcamp.bankapp.kafka.consumer;

import com.nttdata.bootcamp.bankapp.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaStringConsumer {

    Logger logger = LoggerFactory.getLogger(KafkaStringConsumer.class);

//    @KafkaListener(topics = "quickstart-events" , groupId = "group_id")
//    public void consume(Transaction transaction) {
//        logger.info("Desean iniciar una transacción: {}", transaction.toString());
//    }

}