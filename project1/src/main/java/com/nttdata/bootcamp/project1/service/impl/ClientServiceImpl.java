package com.nttdata.bootcamp.project1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.project1.model.Client;
import com.nttdata.bootcamp.project1.model.Clienttype;
import com.nttdata.bootcamp.project1.repository.IClientRepository;
import com.nttdata.bootcamp.project1.service.IClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements IClientService{

	@Autowired
	IClientRepository iClientRepository;
	
	@Override
	public void save(Client e) {
		iClientRepository.save(e).subscribe();
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
