package com.nttdata.bootcamp.project1.service;

import com.nttdata.bootcamp.project1.model.AccountPayment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountPaymentService {
	public void save(AccountPayment e);
	Mono<AccountPayment> findById(Integer id);
    Flux<AccountPayment> findAll();
    Mono<AccountPayment> update(AccountPayment e);
    Mono<Void> delete(Integer id);
}
