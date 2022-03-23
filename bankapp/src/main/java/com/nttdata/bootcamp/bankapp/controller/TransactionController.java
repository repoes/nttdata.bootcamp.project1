/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.bootcamp.bankapp.controller;

import com.nttdata.bootcamp.bankapp.config.AppConfig;
import com.nttdata.bootcamp.bankapp.model.Product;
import com.nttdata.bootcamp.bankapp.model.Transaction;
import com.nttdata.bootcamp.bankapp.service.IProductService;
import com.nttdata.bootcamp.bankapp.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author erojaalf
 */
@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ITransactionService iTransactionService;

    @Autowired
    private AppConfig appConfig;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> save(@RequestBody Transaction transaction) {
        return iTransactionService.save(transaction);
    }

    @GetMapping("/list")
    public Flux<Transaction> list() {
        Flux<Transaction> list = iTransactionService.findAll().map(transaction -> {
            return transaction;
        });
        list.subscribe(transaction -> log.info(transaction.toString()));
        return list;
    }
}
