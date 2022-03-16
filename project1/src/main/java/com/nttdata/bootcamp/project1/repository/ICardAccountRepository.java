package com.nttdata.bootcamp.project1.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.nttdata.bootcamp.project1.model.CardAccount;

import reactor.core.publisher.Mono;

@Repository
public interface ICardAccountRepository extends ReactiveMongoRepository<CardAccount, Integer> {
	Mono<CardAccount> findByAccountClientId(int accountId);
	Mono<CardAccount> findByCardNumber(String cardNumber);
        Mono<CardAccount> findByCardNumberAndAccountPrincipal(String cardNumber,boolean toCardPrincipal);
}
