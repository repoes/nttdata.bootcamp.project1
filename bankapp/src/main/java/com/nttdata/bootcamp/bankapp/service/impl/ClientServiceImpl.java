package com.nttdata.bootcamp.bankapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.bankapp.model.Client;
import com.nttdata.bootcamp.bankapp.model.Clienttype;
import com.nttdata.bootcamp.bankapp.repository.IClientRepository;
import com.nttdata.bootcamp.bankapp.repository.IClienttypeRepository;
import com.nttdata.bootcamp.bankapp.service.IClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements IClientService{

	@Autowired
	IClientRepository iClientRepository;
	
	@Autowired
	IClienttypeRepository iClienttypeRepository;
	
	@Override
	public Mono<Client> save(Client e) {
		return iClienttypeRepository.findById(e.getClienttype().getId())
		.flatMap(data -> {
			e.setClienttype(data);
			return iClientRepository.save(e);
		});
	}

	@Override
	public Mono<Client> findById(Integer id) {
		return iClientRepository.findById(id);
	}

	@Override
	public Flux<Client> findAll() {
		return iClientRepository.findAll();
	}

	@Override
	public Mono<Client> update(Client e) {
		return iClientRepository.save(e);
	}

	@Override
	public Mono<Void> delete(Integer id) {
		return iClientRepository.deleteById(id);
	}

}
