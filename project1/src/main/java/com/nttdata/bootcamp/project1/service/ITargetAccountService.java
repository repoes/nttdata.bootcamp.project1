package com.nttdata.bootcamp.project1.service;

import com.nttdata.bootcamp.project1.model.TargetAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITargetAccountService {
	Mono<?> save(TargetAccount targetAccount);
	Flux<TargetAccount> list();
}
