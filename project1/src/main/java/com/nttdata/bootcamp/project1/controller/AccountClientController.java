package com.nttdata.bootcamp.project1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.project1.config.AppConfig;
import com.nttdata.bootcamp.project1.model.AccountClient;
import com.nttdata.bootcamp.project1.service.IAccountClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/accountClient")
public class AccountClientController {
	
	private static final Logger log = LoggerFactory.getLogger(AccountClientController.class);
	
	@Autowired
	IAccountClientService accountClientService;
	
	
	@Autowired
	private AppConfig appConfig;
	
	@PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> save (@RequestBody AccountClient account) throws RuntimeException{
		return accountClientService.save(account)
				.map(result -> "Cuenta del cliente: " + result.getClient().getName() + " creada!")
				.onErrorResume(ex-> Mono.just(ex.getMessage()));
    }
	@PutMapping("/update")
    public Mono<String> update (@RequestBody AccountClient account){
		return accountClientService.update(account)
				.map(result -> "Cuenta del cliente: " + result.getClient().getName() + " actualizada!")
				.onErrorResume(ex-> Mono.just(ex.getMessage()));
    }
	@GetMapping("/list")
	public Flux<AccountClient> list() {
		log.info(appConfig.toString());
		Flux<AccountClient> list = accountClientService.findAll().map(clienttype -> {
			return clienttype;
		});
		list.subscribe(prod -> log.info(prod.toString()));
		return list;
	}

	@GetMapping("/list/{id}")
	public Flux<String> listPaymentByIdAccClient(@PathVariable int id) {
		log.info(appConfig.toString());
		return accountPaymentService.findAccountPaymentByAccClientId(id);
	}
}
