package com.nttdata.bootcamp.bankapp.service.impl;

import com.nttdata.bootcamp.bankapp.controller.ClientController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.bankapp.model.Product;
import com.nttdata.bootcamp.bankapp.repository.IProductRepository;
import com.nttdata.bootcamp.bankapp.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements IProductService {
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    IProductRepository iProductRepository;

    @Override
    public void save(Product e) {
        iProductRepository.save(e).subscribe();
    }

    @Override
    public Mono<Product> findById(Integer id) {
        return iProductRepository.findById(id);
    }

    @Override
    public Flux<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Mono<Product> update(Product e) {
        return iProductRepository.save(e);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return iProductRepository.deleteById(id);
    }
    
    @Cacheable(value = "products")
    @Override
    public Iterable<Product> findAllRedis() {
        log.warn("Liste los productos y me almacene en memoria");
        return iProductRepository.findAll().toIterable();
    }

}
