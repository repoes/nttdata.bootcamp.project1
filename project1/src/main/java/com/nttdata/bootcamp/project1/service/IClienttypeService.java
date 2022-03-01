package com.nttdata.bootcamp.project1.service;

import com.nttdata.bootcamp.project1.model.Clienttype;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClienttypeService {
	public void save(Clienttype e);
	Mono<Clienttype> findById(Integer id);
    Flux<Clienttype> findAll();
    Mono<Clienttype> update(Clienttype e);
    Mono<Void> delete(Integer id);
}
