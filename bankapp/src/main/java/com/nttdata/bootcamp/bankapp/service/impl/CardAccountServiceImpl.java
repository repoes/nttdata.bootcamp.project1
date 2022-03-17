package com.nttdata.bootcamp.bankapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.bankapp.model.CardAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.nttdata.bootcamp.bankapp.repository.ICardAccountRepository;
import com.nttdata.bootcamp.bankapp.service.ICardAccountService;

@Service
public class CardAccountServiceImpl implements ICardAccountService {
	
	@Autowired
	ICardAccountRepository cardRepository;
        
        
	@Override
	public Mono<?> save(CardAccount targetAccount) {
		// TODO Auto-generated method stub
		return cardRepository.findByAccountClientId(targetAccount.getAccountClient().getId())
		.map( data -> {
			throw new RuntimeException("Esta cuenta ya esta asociada a una tarjeta");
		}).switchIfEmpty( cardRepository.save(targetAccount));
		
	}
	
	private Mono<String> getAmountPrincipal(String cardNumber){
		return cardRepository.findByCardNumber(cardNumber).filter( data -> {
			if(data.isAccountPrincipal()) {
				return true;
			}
			return false;
		})
		.flatMap(data -> Mono.just("Saldo cuenta principal: "+data.getAccountClient().getAmount()));
	}

	@Override
	public Flux<CardAccount> list() {
		// TODO Auto-generated method stub
		return cardRepository.findAll();
	}
}
