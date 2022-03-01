package com.nttdata.bootcamp.project1.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.project1.model.Clienttype;
import com.nttdata.bootcamp.project1.model.Producttype;

@Repository
public interface IProducttypeRepository extends ReactiveMongoRepository<Producttype, Integer>{

}
