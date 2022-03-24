package com.nttdata.bootcamp.bankapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.bankapp.model.CurrencyExchange;
import com.nttdata.bootcamp.bankapp.repository.ICurrencyExchangeRepository;
import com.nttdata.bootcamp.bankapp.service.ICurrencyExchangeService;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrencyExchangeImpl implements ICurrencyExchangeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeImpl.class);
    
    @Autowired
    ICurrencyExchangeRepository iCurrencyExchangeRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;
    
    public CurrencyExchangeImpl(@Qualifier("kafkaStringTemplate") KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @Override
    public void save(CurrencyExchange e) {
        iCurrencyExchangeRepository.save(e).subscribe();
    }

    @Override
    public Mono<CurrencyExchange> findById(String id) {
        return iCurrencyExchangeRepository.findById(id);
    }

    @Override
    public Flux<CurrencyExchange> findAll() {
        return iCurrencyExchangeRepository.findAll();
    }

    @Override
    public Mono<CurrencyExchange> update(CurrencyExchange e) {
        return iCurrencyExchangeRepository.save(e);
    }

    @Override
    public Mono<Void> delete(String id) {
        return iCurrencyExchangeRepository.deleteById(id);
    }
    @Override
    public void sendExchangeDay() {
        LOGGER.info("Producing sendExchangeDay {}", LocalDate.now().toString());
        iCurrencyExchangeRepository.findByDay(LocalDate.now().toString()).subscribe(data -> {
            this.kafkaTemplate.send("quickstart-events", "ID: "+ data.getId() + 
                    " TIPO DE CAMBIO DEL DIA: "+ data.getCurrencyFrom() +" -> "+ data.getCurrencyTo() +", VALOR VENTA: "+ data.getSellValue() +", VALOR COMPRA: " + data.getPurchaseValue());
        });
//        this.kafkaTemplate.send("quickstart-events", "TIPO DE CAMBIO DEL DIA: PEN -> BOOTCOIN, VALOR VENTA: 100, VALOR COMPRA: 99");
        
//        this.kafkaTemplate.send("quickstart-events", data.toString());
//        iCurrencyExchangeRepository.findByDay(LocalDate.now()).subscribe(data -> {
//            this.kafkaTemplate.send("quickstart-events", data.toString());
//        });  
    }
//    @KafkaListener(topics = "quickstart-events" , groupId = "group_id")
//    public void consumeExchangeDay(String message) {
//        LOGGER.info("Consuming Message {}", message);
//    }
}
