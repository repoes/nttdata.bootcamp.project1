package com.nttdata.bootcamp.project1.service;

import com.nttdata.bootcamp.project1.model.Client;
import com.nttdata.bootcamp.project1.model.Clienttype;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {
	public Mono<Client> save(Client e);
	Mono<Client> findById(Integer id);
    Flux<Client> findAll();
    Mono<Client> update(Client e);
    Mono<Void> delete(Integer id);
}
