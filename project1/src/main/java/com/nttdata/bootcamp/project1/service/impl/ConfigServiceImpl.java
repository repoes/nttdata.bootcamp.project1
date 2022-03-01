package com.nttdata.bootcamp.project1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.project1.model.Config;
import com.nttdata.bootcamp.project1.repository.IConfigRepository;
import com.nttdata.bootcamp.project1.service.IConfigService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ConfigServiceImpl implements IConfigService{

	@Autowired
	IConfigRepository iConfigRepository;
	
	@Override
	public void save(Config e) {
		iConfigRepository.save(e).subscribe();
	}

	@Override
	public Mono<Config> findById(Integer id) {
		return iConfigRepository.findById(id);
	}

	@Override
	public Flux<Config> findAll() {
		return iConfigRepository.findAll();
	}

	@Override
	public Mono<Config> update(Config e) {
		return iConfigRepository.save(e);
	}

	@Override
	public Mono<Void> delete(Integer id) {
		return iConfigRepository.deleteById(id);
	}


}
