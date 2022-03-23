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
import com.nttdata.bootcamp.bankapp.model.Client;
import com.nttdata.bootcamp.bankapp.service.IClientService;
import org.springframework.cache.annotation.Cacheable;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/client")
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    IClientService clientService;

    @Autowired
    private AppConfig appConfig;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<?> save(@RequestBody Client client) {
        return clientService.save(client)
                .map(result -> "Cliente creado!");
    }

    @GetMapping("/list")
//    @Cacheable(value = "users")
    public Flux<Client> list() {
        log.info("Liste los clientes y me almacene en memoria");
        Flux<Client> list = clientService.findAll().map(clienttype -> {
            return clienttype;
        });
        list.subscribe(prod -> log.info(prod.toString()));
        return list;
    }
}
