package com.nttdata.bootcamp.project1.service;

import com.nttdata.bootcamp.project1.model.Productsubtype;
import com.nttdata.bootcamp.project1.model.Producttype;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductsubtypeService {
	public void save(Productsubtype e);
	Mono<Productsubtype> findById(Integer id);
    Flux<Productsubtype> findAll();
    Mono<Productsubtype> update(Productsubtype e);
    Mono<Void> delete(Integer id);
}
