package com.nttdata.bootcamp.bankapp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.bankapp.model.Client;
import com.nttdata.bootcamp.bankapp.model.Clienttype;
import com.nttdata.bootcamp.bankapp.model.CurrencyExchange;
import java.time.LocalDate;
import reactor.core.publisher.Mono;

@Repository
public interface ICurrencyExchangeRepository extends ReactiveMongoRepository<CurrencyExchange, String>{
    public Mono<CurrencyExchange> findByDay(LocalDate day);
}
