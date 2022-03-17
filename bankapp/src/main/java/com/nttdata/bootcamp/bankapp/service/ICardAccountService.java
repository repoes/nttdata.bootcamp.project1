package com.nttdata.bootcamp.bankapp.service;

import com.nttdata.bootcamp.bankapp.model.CardAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICardAccountService {
	Mono<?> save(CardAccount targetAccount);
	Flux<CardAccount> list();
}
