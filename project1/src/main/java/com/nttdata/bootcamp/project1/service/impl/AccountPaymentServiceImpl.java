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
	public Mono<?> save(AccountPayment e) {
		//buscar cuenta por accountclient.name
		//si no encuentra error
		//actualizar monto accountcliente
		//guardar pago
		return iAccountClientRepository.findById(e.getAccountClient().getId()).map( data->{
			if(e.getMovementtype().equals("DEPOSITO")) {
				data.setAmount(data.getAmount().add(e.getAmount()));
			}else if(e.getMovementtype().equals("RETIRO")){
				if(data.getAmount().compareTo(e.getAmount()) == -1) {
					throw new RuntimeException("No cuenta con saldo suficiente en su cuenta");
				}
				data.setAmount(data.getAmount().subtract(e.getAmount()));
			}
			else{
				throw new RuntimeException("Solo se permiten los valores DEPOSITO/RETIRO");
			}
			iAccountPaymentRepository.save(e);
			return data;
		})
		.switchIfEmpty(Mono.error(new Exception("No se encuentra la cuenta")))
		.flatMap(data -> iAccountClientRepository.save(data));
		
//		return mono;
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
