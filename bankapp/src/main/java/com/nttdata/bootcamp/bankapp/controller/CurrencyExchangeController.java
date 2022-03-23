/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.bootcamp.bankapp.controller;

import com.nttdata.bootcamp.bankapp.kafka.producer.KafkaStringProducer;
import com.nttdata.bootcamp.bankapp.model.CurrencyExchange;
import com.nttdata.bootcamp.bankapp.service.ICurrencyExchangeService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Flux;
//import reactor.kafka.receiver.KafkaReceiver;
//import reactor.kafka.receiver.ReceiverRecord;
/**
 *
 * @author erojaalf
 */
@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {
    @Autowired
    ICurrencyExchangeService iCurrencyExchangeService;
    
//    KafkaReceiver<String,String> kafkaReceiver;
//    
//    @Autowired
//    CurrencyExchangeController(KafkaReceiver<String,String> kafkaReceiver) {
//        this.kafkaReceiver = kafkaReceiver;
//    }
    
    @PostMapping(value = "/day-exchange")
    public void sendMessageToKafkaTopic() {
        this.iCurrencyExchangeService.sendExchangeDay();
    }
    
//    @GetMapping(value = "/day-exchange")
//    public void getDayExchange() {
//        this.iCurrencyExchangeService.consumeExchangeDay(message);
//    }
//    @GetMapping(value = "/day-exchange", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    Flux getEventsFlux(){
//        Flux<ReceiverRecord<String,String>> kafkaFlux = kafkaReceiver.receive();
//        return kafkaFlux.checkpoint("Messages are started being consumed")
//                .log()
//                .doOnNext(r -> r.receiverOffset().acknowledge()).map(ReceiverRecord::value).checkpoint("Messages are done consumed");
//    }
}
