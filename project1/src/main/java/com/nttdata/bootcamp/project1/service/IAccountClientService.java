package com.nttdata.bootcamp.project1.service;

import java.util.List;

import com.nttdata.bootcamp.project1.model.AccountClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountClientService {
	public Mono<AccountClient> save(AccountClient e) ;
	Mono<AccountClient> findById(Integer id);
    Flux<AccountClient> findAll();
    public Flux<String> findByClient(int clientId);
    Mono<AccountClient> update(AccountClient e);
    Mono<Void> delete(Integer id);
    public Flux<AccountClient> findByClientId(int id);
}
