package com.nttdata.bootcamp.project1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.project1.model.AccountPayment;
import com.nttdata.bootcamp.project1.repository.IAccountPaymentRepository;
import com.nttdata.bootcamp.project1.service.IAccountPaymentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountPaymentServiceImpl implements IAccountPaymentService{

	@Autowired
	IAccountPaymentRepository iAccountPaymentRepository;
	
	@Override
	public void save(AccountPayment e) {
		iAccountPaymentRepository.save(e).subscribe();
	}

	@Override
	public Mono<AccountPayment> findById(Integer id) {
		return iAccountPaymentRepository.findById(id);
	}

	@Override
	public Flux<AccountPayment> findAll() {
		return iAccountPaymentRepository.findAll();
	}

	@Override
	public Mono<AccountPayment> update(AccountPayment e) {
		return iAccountPaymentRepository.save(e);
	}

	@Override
	public Mono<Void> delete(Integer id) {
		return iAccountPaymentRepository.deleteById(id);
	}


}
