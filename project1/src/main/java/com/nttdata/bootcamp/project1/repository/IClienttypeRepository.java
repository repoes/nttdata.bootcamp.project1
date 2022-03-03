package com.nttdata.bootcamp.project1.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.project1.model.Clienttype;

@Repository
public interface IClienttypeRepository extends ReactiveMongoRepository<Clienttype, Integer>{

}
