package com.nttdata.bootcamp.project1.service;

import com.nttdata.bootcamp.project1.model.Config;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IConfigService {
	public void save(Config e);
	Mono<Config> findById(Integer id);
    Flux<Config> findAll();
    Mono<Config> update(Config e);
    Mono<Void> delete(Integer id);
}
