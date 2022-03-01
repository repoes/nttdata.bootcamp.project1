package com.nttdata.bootcamp.project1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.project1.model.Account;
import com.nttdata.bootcamp.project1.repository.IAccountRepository;
import com.nttdata.bootcamp.project1.service.IAccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements IAccountService{

	@Autowired
	IAccountRepository iAccountRepository;
	
	@Override
	public void save(Account e) {
		iAccountRepository.save(e).subscribe();
	}

	@Override
	public Mono<Account> findById(Integer id) {
		return iAccountRepository.findById(id);
	}

	@Override
	public Flux<Account> findAll() {
		return iAccountRepository.findAll();
	}

	@Override
	public Mono<Account> update(Account e) {
		return iAccountRepository.save(e);
	}

	@Override
	public Mono<Void> delete(Integer id) {
		return iAccountRepository.deleteById(id);
	}


}
