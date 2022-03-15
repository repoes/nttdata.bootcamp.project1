package com.nttdata.bootcamp.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.project1.model.Product;
import com.nttdata.bootcamp.project1.model.TargetAccount;
import com.nttdata.bootcamp.project1.service.ITargetAccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/target")
public class TargetAccountController {
	
	@Autowired
	private ITargetAccountService targetService;
	
	@PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> save (@RequestBody TargetAccount targetAccount) throws RuntimeException{
		return targetService.save(targetAccount)
				.map(result -> "Cuenta Asociada!")
				.onErrorResume(ex-> Mono.just(ex.getMessage()));
    }
	
	@GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
	public Flux<TargetAccount> list() {
		return targetService.list().map(targetAccount -> {
			return targetAccount;
		});
	}
}
