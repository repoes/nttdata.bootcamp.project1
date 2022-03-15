package com.nttdata.bootcamp.project1.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.nttdata.bootcamp.project1.model.TargetAccount;

import reactor.core.publisher.Mono;

@Repository
public interface ITargetAccountRepository extends ReactiveMongoRepository<TargetAccount, Integer> {
	Mono<TargetAccount> findByAccountClientId(int accountId);
	Mono<TargetAccount> findByTargetNumber(String targetNumber);
}
