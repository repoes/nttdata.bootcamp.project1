package com.nttdata.bootcamp.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.bankapp.model.Product;
import com.nttdata.bootcamp.bankapp.model.CardAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.nttdata.bootcamp.bankapp.service.ICardAccountService;
import com.nttdata.bootcamp.bankapp.service.impl.CardAccountServiceImpl;

@RestController
@RequestMapping("/card")
public class TargetAccountController {
	
	@Autowired
	private ICardAccountService targetService;
	
	@PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> save (@RequestBody CardAccount targetAccount) throws RuntimeException{
		return targetService.save(targetAccount)
				.map(result -> "Cuenta Asociada!")
				.onErrorResume(ex-> Mono.just(ex.getMessage()));
    }
	
	@GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
	public Flux<CardAccount> list() {
		return targetService.list().map(targetAccount -> {
			return targetAccount;
		});
	}
	
	@GetMapping("/principal/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
	public Mono<String> getAccountPrincipal(@PathVariable("cardNumber") String cardNumber) {
		return targetService.getAmountPrincipal(cardNumber);
	}
}
