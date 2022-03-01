package com.nttdata.bootcamp.project1.service;

import com.nttdata.bootcamp.project1.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {
	public void save(Product e);
	Mono<Product> findById(Integer id);
    Flux<Product> findAll();
    Mono<Product> update(Product e);
    Mono<Void> delete(Integer id);
}
