package com.nttdata.bootcamp.bankapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.bootcamp.bankapp.model.AccountPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.bankapp.model.Transaction;
import com.nttdata.bootcamp.bankapp.repository.IAccountClientRepository;
import com.nttdata.bootcamp.bankapp.repository.ITransactionRepository;
import com.nttdata.bootcamp.bankapp.service.IAccountPaymentService;
import com.nttdata.bootcamp.bankapp.service.ICurrencyExchangeService;
import com.nttdata.bootcamp.bankapp.service.ITransactionService;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements ITransactionService {

    Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

    public static final String TOPIC = "quickstart-events";
    public static final String TOPIC_TRANSACTIONS = "topicTransactions";
    @Autowired
    ITransactionRepository iTransactionRepository;

    @Autowired
    IAccountClientRepository iAccountClientRepository;

    @Autowired
    ICurrencyExchangeService iCurrencyExchangeService;
    
    @Autowired
    IAccountPaymentService iAccountPaymentService;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Transaction transaction;

    @Override
    public Mono<String> confirmTransaction(String valueConfirm) {
        LOGGER.info(transaction.toString());
        if (valueConfirm.equalsIgnoreCase("SI")) {
            if(this.transaction == null){
                LOGGER.info("No hay transacción en espera");
                return Mono.just("No hay transacción en espera");
            }
            kafkaTemplate.send(TOPIC, "TRANSACCIÓN ACEPTADA!");
            return save(this.transaction).flatMap(data -> {
                kafkaTemplate.send(TOPIC, data);
                return Mono.just("Transacción enviada!");
            });
        } else {
            kafkaTemplate.send(TOPIC, "NO ACEPTADO :(");
            return Mono.just("No aceptó :(");
        }
    }

    @KafkaListener(topics = TOPIC_TRANSACTIONS, groupId = "group_id")
    public void consumeTransaction(String message) throws JsonProcessingException {
        LOGGER.info("consuming tansaction...");
        LOGGER.info(message.toString());
        ObjectMapper mapper = new ObjectMapper();
//        Transaction transactionTemp = new Transaction();

        transaction = mapper.readValue(message, Transaction.class);
//        transactionTemp.setAmountFrom(transactionReceived.getAmountFrom());
//        transactionTemp.setAmountTo(transactionReceived.getAmountTo());
//        LOGGER.info(String.valueOf(transactionReceived.getAccountFromId()));
//        return save(transactionReceived);
//        iAccountClientRepository.findById(transactionReceived.getAccountFromId())
//                .switchIfEmpty(Mono.error(new Exception("No se encuentra la cuenta getAccountFrom")))
//                .map(data -> {
//                    LOGGER.info("Encontrado");
//                    transactionTemp.setAccountFrom(data);
//                    return iAccountClientRepository.findById(transactionReceived.getAccountToId())
//                            .map(data2 -> {
//                                LOGGER.info("Encontrado2");
//                                transactionTemp.setAccountTo(data2);
//                                transaction = transactionTemp;
//                                LOGGER.info(transaction.toString());
//                                return data2;
//                            })
//                            .switchIfEmpty(Mono.error(new Exception("No se encuentra la cuenta getAccountTo")));
//
//                });

    }
    @Override
    public Mono<String> save(Transaction e) {
        //findById accountclient1
        //findById accountclient2
        //grabar accountpayment 1
        //grabar accountpayment 2
        return iAccountClientRepository.findById(e.getAccountFromId())
                .switchIfEmpty(Mono.error(new Exception("No se encuentra la cuenta getAccountFrom")))
                .map(data -> {
                    return iAccountClientRepository.findById(e.getAccountToId());

                })
                .switchIfEmpty(Mono.error(new Exception("No se encuentra la cuenta getAccountTo")))
                .flatMap(data2 -> {
                    return iTransactionRepository.save(e).map(mapper -> "Transacción creada: "+ mapper.getId());
                })
                .onErrorResume(ex -> Mono.just(ex.getMessage()));
    }
//    @Override
//    public Mono<String> save(Transaction e) {
//        //findById accountclient1
//        //findById accountclient2
//        //grabar
//        return iAccountClientRepository.findById(e.getAccountFromId())
//                .map(data -> {
//                    AccountPayment acc = new AccountPayment();
//                    acc.setAccountClient(data);
//                    return iCurrencyExchangeService.findById(e.getCurrencyExchangeRateId()).map(data2 -> {
//                        acc.setAmount(e.getAmount().multiply(data2.getPurchaseValue()));
//                        acc.setMovementtype("RETIRO");
//                        iAccountPaymentService.save(acc).subscribe();
//                        return iAccountClientRepository.findById(e.getAccountToId()).map(mapper -> {
//                            return iTransactionRepository.save(e).map(mapper2 -> {
//                        
//                                AccountPayment acc2 = new AccountPayment();
//                                acc2.setAccountClient(mapper);
//                                acc2.setAmount(e.getAmount());
//                                acc2.setMovementtype("DEPOSITO");
//                                iAccountPaymentService.save(acc2).subscribe();
//                                return Mono.just("Transacción creada: " + mapper2.getId());
//                            });
//                        })
//                        .switchIfEmpty(Mono.error(new Exception("No se encuentra la cuenta getAccountFrom")));
//                    });
//
//                });
//    }

    @Override
    public Mono<Transaction> findById(String id) {
        return iTransactionRepository.findById(id);
    }

    @Override
    public Flux<Transaction> findAll() {
        return iTransactionRepository.findAll();
    }

    @Override
    public Mono<Transaction> update(Transaction e) {
        return iTransactionRepository.save(e);
    }

    @Override
    public Mono<Void> delete(String id) {
        return iTransactionRepository.deleteById(id);
    }

}
