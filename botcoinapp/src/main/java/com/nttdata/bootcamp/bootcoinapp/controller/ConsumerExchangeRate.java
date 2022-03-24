/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.bootcamp.bootcoinapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nttdata.bootcamp.bootcoinapp.dto.CurrencyExchange;
import com.nttdata.bootcamp.bootcoinapp.dto.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author erojaalf
 */
@RestController
@RequestMapping("/bootcoinapp")
public class ConsumerExchangeRate {
    public static final String TOPIC = "quickstart-events";
    public static final String TOPIC_TRANSACTIONS = "topicTransactions";
    Logger LOGGER = LoggerFactory.getLogger(ConsumerExchangeRate.class);
    
//    @Autowired
//    KafkaTemplate<String,CurrencyExchange> kafkaTemplateExchangeRate;
    
    @Autowired
    KafkaTemplate<String,String> kafkaTemplateTransaction;
    
    @KafkaListener(topics = "quickstart-events"  , groupId = "group_id")
    public void consume(String message) {
        LOGGER.info("Esperando mensajes y respuestas del banco...");
        LOGGER.info(message);
    }
    @PostMapping(value = "/init-transaction")
    public String sendMessageToKafkaTopic(@RequestBody Transaction transaction) throws JsonProcessingException {
        LOGGER.info("sending tansaction...");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(transaction);
        this.kafkaTemplateTransaction.send(TOPIC_TRANSACTIONS, json);
        return "transaction " + transaction.toString() + " sended succesfully";
     }
    
}
