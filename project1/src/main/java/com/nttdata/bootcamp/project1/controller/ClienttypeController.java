package com.nttdata.bootcamp.project1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.project1.config.AppConfig;
import com.nttdata.bootcamp.project1.model.Clienttype;
import com.nttdata.bootcamp.project1.service.IClienttypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/clienttype")
public class ClienttypeController {
	
	private static final Logger log = LoggerFactory.getLogger(ClienttypeController.class);
	
	@Autowired
	IClienttypeService clienttypeService;
	
	@Autowired
	private AppConfig appConfig;
	
	@PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save (@RequestBody Clienttype clienttype){
		clienttypeService.save(clienttype);
    }
	@GetMapping("/list")
	public Flux<Clienttype> list() {
		log.info(appConfig.toString());
		Flux<Clienttype> list = clienttypeService.findAll().map(clienttype -> {
			
//			clienttype.setNombre(producto.getNombre().toUpperCase());
			return clienttype;
		});
		list.subscribe(prod -> log.info(prod.toString()));
		return list;
	}
}
