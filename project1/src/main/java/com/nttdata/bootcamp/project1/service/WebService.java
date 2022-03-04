package com.nttdata.bootcamp.project1.service;

import reactor.core.publisher.Mono;

public interface WebService {
	public Mono<String> getMessage();
}
