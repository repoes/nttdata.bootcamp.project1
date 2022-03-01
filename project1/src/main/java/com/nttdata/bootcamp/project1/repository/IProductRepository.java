package com.nttdata.bootcamp.project1.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.project1.model.Account;
import com.nttdata.bootcamp.project1.model.Clienttype;
import com.nttdata.bootcamp.project1.model.Product;
import com.nttdata.bootcamp.project1.model.Productsubtype;
import com.nttdata.bootcamp.project1.model.Producttype;

@Repository
public interface IProductRepository extends ReactiveMongoRepository<Product, Integer>{

}
