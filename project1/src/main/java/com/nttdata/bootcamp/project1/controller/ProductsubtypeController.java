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

import com.nttdata.bootcamp.project1.model.Productsubtype;
import com.nttdata.bootcamp.project1.service.IProductsubtypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/productsubtype")
public class ProductsubtypeController {
	
	private static final Logger log = LoggerFactory.getLogger(ProductsubtypeController.class);
	
	@Autowired
	IProductsubtypeService productsubtypeService;
	
	@PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save (@RequestBody Productsubtype clienttype){
		productsubtypeService.save(clienttype);
    }
	@GetMapping("/list")
	public Flux<Productsubtype> list() {
		
		Flux<Productsubtype> list = productsubtypeService.findAll().map(clienttype -> {
			
//			clienttype.setNombre(producto.getNombre().toUpperCase());
			return clienttype;
		});
		list.subscribe(prod -> log.info(prod.toString()));
		return list;
	}
}
