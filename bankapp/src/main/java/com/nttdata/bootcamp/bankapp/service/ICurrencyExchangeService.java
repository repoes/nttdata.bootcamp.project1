package com.nttdata.bootcamp.bankapp.service;

import com.nttdata.bootcamp.bankapp.model.CurrencyExchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICurrencyExchangeService {

    public void save(CurrencyExchange e);

    Mono<CurrencyExchange> findById(String id);

    Flux<CurrencyExchange> findAll();

    Mono<CurrencyExchange> update(CurrencyExchange e);

    Mono<Void> delete(String id);

    public void sendExchangeDay();
}
