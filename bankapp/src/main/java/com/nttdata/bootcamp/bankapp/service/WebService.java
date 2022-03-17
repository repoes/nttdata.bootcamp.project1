package com.nttdata.bootcamp.bankapp.service;

import reactor.core.publisher.Mono;

public interface WebService {
	public Mono<String> getMessage();
}
