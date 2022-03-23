package com.nttdata.bootcamp.bankapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.bankapp.model.Transaction;
import com.nttdata.bootcamp.bankapp.repository.IAccountClientRepository;
import com.nttdata.bootcamp.bankapp.repository.ITransactionRepository;
import com.nttdata.bootcamp.bankapp.service.ITransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    ITransactionRepository iTransactionRepository;

    @Autowired
    IAccountClientRepository iAccountClientRepository;

    @Override
    public Mono<String> save(Transaction e) {
        //findById accountclient1
        //findById accountclient2
        //grabar
        return iAccountClientRepository.findById(e.getAccountFrom().getId())
                .switchIfEmpty(Mono.error(new Exception("No se encuentra la cuenta getAccountFrom")))
                .map(data -> {
                    return iAccountClientRepository.findById(e.getAccountTo().getId());

                })
                .switchIfEmpty(Mono.error(new Exception("No se encuentra la cuenta getAccountTo")))
                .flatMap(data2 -> {
                    return iTransactionRepository.save(e).map(mapper -> "Transacción creada: "+ mapper.getId());
                })
                .onErrorResume(ex -> Mono.just(ex.getMessage()));
    }

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
