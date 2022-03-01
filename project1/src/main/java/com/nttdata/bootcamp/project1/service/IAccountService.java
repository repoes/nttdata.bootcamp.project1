package com.nttdata.bootcamp.project1.service;

import com.nttdata.bootcamp.project1.model.Account;
import com.nttdata.bootcamp.project1.model.Producttype;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountService {
	public void save(Account e);
	Mono<Account> findById(Integer id);
    Flux<Account> findAll();
    Mono<Account> update(Account e);
    Mono<Void> delete(Integer id);
}
