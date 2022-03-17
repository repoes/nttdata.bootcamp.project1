package com.nttdata.bootcamp.bankapp.service;

import com.nttdata.bootcamp.bankapp.model.Client;
import com.nttdata.bootcamp.bankapp.model.Clienttype;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {
	public Mono<Client> save(Client e);
	Mono<Client> findById(Integer id);
    Flux<Client> findAll();
    Mono<Client> update(Client e);
    Mono<Void> delete(Integer id);
}
