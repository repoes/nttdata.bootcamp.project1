package com.nttdata.bootcamp.project1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.project1.model.Producttype;
import com.nttdata.bootcamp.project1.repository.IProducttypeRepository;
import com.nttdata.bootcamp.project1.service.IProducttypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProducttypeServiceImpl implements IProducttypeService{

	@Autowired
	IProducttypeRepository iProducttypeRepository;
	
	@Override
	public void save(Producttype e) {
		iProducttypeRepository.save(e).subscribe();
	}

	@Override
	public Mono<Producttype> findById(Integer id) {
		return iProducttypeRepository.findById(id);
	}

	@Override
	public Flux<Producttype> findAll() {
		return iProducttypeRepository.findAll();
	}

	@Override
	public Mono<Producttype> update(Producttype e) {
		return iProducttypeRepository.save(e);
	}

	@Override
	public Mono<Void> delete(Integer id) {
		return iProducttypeRepository.deleteById(id);
	}


}
