package com.nttdata.bootcamp.project1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.project1.model.TargetAccount;
import com.nttdata.bootcamp.project1.repository.ITargetAccountRepository;
import com.nttdata.bootcamp.project1.service.ITargetAccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TargetAccountServiceImpl implements ITargetAccountService {
	
	@Autowired
	ITargetAccountRepository targetRepository;
	@Override
	public Mono<?> save(TargetAccount targetAccount) {
		// TODO Auto-generated method stub
		return targetRepository.findByAccountClientId(targetAccount.getAccountClient().getId())
		.map( data -> {
			throw new RuntimeException("Esta cuenta ya esta asociada a una tarjeta");
		}).switchIfEmpty( targetRepository.save(targetAccount));
		
	}
	
	private Mono<String> getAmountPrincipal(String cardNumber){
		return targetRepository.findByTargetNumber(cardNumber).filter( data -> {
			if(data.getAccountClient().isToCardPrincipal()) {
				return true;
			}
			return false;
		})
		.flatMap(data -> Mono.just("Saldo cuenta principal: "+data.getAccountClient().getAmount()));
	}

	@Override
	public Flux<TargetAccount> list() {
		// TODO Auto-generated method stub
		return targetRepository.findAll();
	}
}
