package com.nttdata.bootcamp.project1.service;

import com.nttdata.bootcamp.project1.model.CardAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICardAccountService {
	Mono<?> save(CardAccount targetAccount);
	Flux<CardAccount> list();
}
