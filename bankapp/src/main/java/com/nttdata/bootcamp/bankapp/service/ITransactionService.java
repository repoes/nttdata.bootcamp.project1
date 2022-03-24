package com.nttdata.bootcamp.bankapp.service;

import com.nttdata.bootcamp.bankapp.model.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionService {

    public Mono<String> save(Transaction e);

    Mono<Transaction> findById(String id);

    Flux<Transaction> findAll();

    Mono<Transaction> update(Transaction e);

    Mono<Void> delete(String id);
    
    public Mono<String> confirmTransaction(String valueConfirm);
}
