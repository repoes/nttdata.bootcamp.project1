package com.nttdata.bootcamp.bankapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.bankapp.model.Clienttype;
import com.nttdata.bootcamp.bankapp.repository.IClienttypeRepository;
import com.nttdata.bootcamp.bankapp.service.IClienttypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienttypeServiceImpl implements IClienttypeService{

	@Autowired
	IClienttypeRepository iClienttypeRepository;
	
	@Override
	public void save(Clienttype clienttype) {
		iClienttypeRepository.save(clienttype).subscribe();
	}

	@Override
	public Mono<Clienttype> findById(Integer id) {
		return iClienttypeRepository.findById(id);
	}

	@Override
	public Flux<Clienttype> findAll() {
		return iClienttypeRepository.findAll();
	}

	@Override
	public Mono<Clienttype> update(Clienttype clienttype) {
		return iClienttypeRepository.save(clienttype);
	}

	@Override
	public Mono<Void> delete(Integer id) {
		return iClienttypeRepository.deleteById(id);
	}

}
