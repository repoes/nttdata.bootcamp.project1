package com.nttdata.bootcamp.bankapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.bankapp.model.Product;
import com.nttdata.bootcamp.bankapp.repository.IProductRepository;
import com.nttdata.bootcamp.bankapp.service.IProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements IProductService{

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


}
