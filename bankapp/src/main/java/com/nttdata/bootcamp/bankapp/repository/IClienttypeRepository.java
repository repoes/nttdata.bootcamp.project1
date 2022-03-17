package com.nttdata.bootcamp.bankapp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.bankapp.model.Clienttype;

@Repository
public interface IClienttypeRepository extends ReactiveMongoRepository<Clienttype, Integer>{

}
