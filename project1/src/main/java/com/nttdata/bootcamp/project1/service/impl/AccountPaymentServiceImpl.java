package com.nttdata.bootcamp.project1.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.project1.model.AccountClient;
import com.nttdata.bootcamp.project1.model.AccountPayment;
import com.nttdata.bootcamp.project1.repository.IAccountClientRepository;
import com.nttdata.bootcamp.project1.repository.IAccountPaymentRepository;
import com.nttdata.bootcamp.project1.service.IAccountPaymentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountPaymentServiceImpl implements IAccountPaymentService{
	private static final Logger log = LoggerFactory.getLogger(AccountClientServiceImpl.class);
	
	@Autowired
	IAccountPaymentRepository iAccountPaymentRepository;
	
	@Autowired
	IAccountClientRepository iAccountClientRepository;
	
	@Override
	public Mono<AccountClient> save(AccountPayment e) {
		//buscar cuenta por accountclient.name
		//si no encuentra error
		//actualizar monto accountcliente
		//guardar pago
		Mono<AccountClient> mono = iAccountClientRepository.findById(e.getAccountClient().getId()).map( data->{
			 data.setAmount(data.getAmount().add(e.getAmount()));
			 iAccountPaymentRepository.save(e);
			 return data;
		})
		.switchIfEmpty(Mono.error(new Exception("No se encuentra la cuenta")))
		.flatMap(data -> iAccountClientRepository.save(data));
		mono.subscribe(data ->{
			log
		});
		return mono.subscribe(data -> {
			iAccountPaymentRepository.save(e);	
		});
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
