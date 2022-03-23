package com.nttdata.bootcamp.bankapp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.bankapp.model.Client;
import com.nttdata.bootcamp.bankapp.model.Clienttype;
import com.nttdata.bootcamp.bankapp.model.Transaction;

@Repository
public interface ITransactionRepository extends ReactiveMongoRepository<Transaction, String>{

}
