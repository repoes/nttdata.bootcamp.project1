package com.nttdata.bootcamp.project1.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.project1.model.Account;
import com.nttdata.bootcamp.project1.model.AccountClient;
import com.nttdata.bootcamp.project1.model.Client;
import com.nttdata.bootcamp.project1.model.Clienttype;
import com.nttdata.bootcamp.project1.model.Product;
import com.nttdata.bootcamp.project1.model.Productsubtype;
import com.nttdata.bootcamp.project1.model.Producttype;

import reactor.core.publisher.Flux;

@Repository
public interface IAccountClientRepository extends ReactiveMongoRepository<AccountClient, Integer>{
	Flux<AccountClient> findByClientId(int clientId);
}
