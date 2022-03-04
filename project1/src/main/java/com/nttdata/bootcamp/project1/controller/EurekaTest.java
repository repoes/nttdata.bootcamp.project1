package com.nttdata.bootcamp.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.project1.service.WebService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("eureka")
public class EurekaTest {

	@Autowired
	@Qualifier("ClientServiceBalanced")
	private WebService clientServiceBalance;
	@GetMapping("/message2")
	public Mono<String> greet(){
		return clientServiceBalance.getMessage();
	}
}
