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

import com.nttdata.bootcamp.project1.model.Producttype;
import com.nttdata.bootcamp.project1.service.IProducttypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/producttype")
public class ProducttypeController {
	
	private static final Logger log = LoggerFactory.getLogger(ProducttypeController.class);
	
	@Autowired
	IProducttypeService clienttypeService;
	
	@PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save (@RequestBody Producttype clienttype){
		clienttypeService.save(clienttype);
    }
	@GetMapping("/list")
	public Flux<Producttype> list() {
		
		Flux<Producttype> list = clienttypeService.findAll().map(clienttype -> {
			
//			clienttype.setNombre(producto.getNombre().toUpperCase());
			return clienttype;
		});
		list.subscribe(prod -> log.info(prod.toString()));
		return list;
	}
}
