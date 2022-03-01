package com.nttdata.bootcamp.project1.service;

import com.nttdata.bootcamp.project1.model.Producttype;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProducttypeService {
	public void save(Producttype e);
	Mono<Producttype> findById(Integer id);
    Flux<Producttype> findAll();
    Mono<Producttype> update(Producttype e);
    Mono<Void> delete(Integer id);
}
