package com.nttdata.bootcamp.bankapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.bankapp.config.AppConfig;
import com.nttdata.bootcamp.bankapp.model.Product;
import com.nttdata.bootcamp.bankapp.service.IProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	IProductService productService;
	
	
	@Autowired
	private AppConfig appConfig;
	
	@PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save (@RequestBody Product product){
		
		productService.save(product);
    }
	@GetMapping("/list")
	public Flux<Product> list() {
		log.info(appConfig.toString());
		Flux<Product> list = productService.findAll().map(product -> {
			return product;
		});
		list.subscribe(prod -> log.info(prod.toString()));
		return list;
	}
}
