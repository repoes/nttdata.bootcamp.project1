package com.nttdata.bootcamp.project1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.project1.model.Productsubtype;
import com.nttdata.bootcamp.project1.repository.IProductsubtypeRepository;
import com.nttdata.bootcamp.project1.service.IProductsubtypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductsubtypeServiceImpl implements IProductsubtypeService{

	@Autowired
	IProductsubtypeRepository iProductsubtypeRepository;
	
	@Override
	public void save(Productsubtype e) {
		iProductsubtypeRepository.save(e).subscribe();
	}

	@Override
	public Mono<Productsubtype> findById(Integer id) {
		return iProductsubtypeRepository.findById(id);
	}

	@Override
	public Flux<Productsubtype> findAll() {
		return iProductsubtypeRepository.findAll();
	}

	@Override
	public Mono<Productsubtype> update(Productsubtype e) {
		return iProductsubtypeRepository.save(e);
	}

	@Override
	public Mono<Void> delete(Integer id) {
		return iProductsubtypeRepository.deleteById(id);
	}


}
