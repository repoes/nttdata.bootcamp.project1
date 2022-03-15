package com.nttdata.bootcamp.project1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.project1.config.AppConfig;
import com.nttdata.bootcamp.project1.dto.AccountPaymentDTO;
import com.nttdata.bootcamp.project1.model.AccountPayment;
import com.nttdata.bootcamp.project1.service.IAccountPaymentService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/accountPayment")
public class AccountPaymentController {

    private static final Logger log = LoggerFactory.getLogger(AccountPaymentController.class);

    @Autowired
    IAccountPaymentService accountPaymentService;

//    @Autowired
//    private AppConfig appConfig;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> save(@RequestBody AccountPayment account) throws RuntimeException {
        return accountPaymentService.save(account)
                .map(result -> "Cuenta actualizada!")
                .onErrorResume(ex -> Mono.just(ex.getMessage()));
    }
    @PostMapping("/saveWithCard")
    @ResponseStatus(HttpStatus.OK)
    public Mono<?> savePaymentByCardNumber(@RequestBody AccountPaymentDTO accountPaymentDTO) {
//        log.info(appConfig.toString());
         return accountPaymentService.savePaymentByCardNumber(accountPaymentDTO)
                .map(result -> "Cuenta actualizada!")
                .onErrorResume(ex -> Mono.just(ex.getMessage()));
    }
    @GetMapping("/list")
    public Flux<AccountPayment> list() {
//        log.info(appConfig.toString());
        Flux<AccountPayment> list = accountPaymentService.findAll().map(clienttype -> {
            return clienttype;
        });
        list.subscribe(prod -> log.info(prod.toString()));
        return list;
    }

    @GetMapping("/list/{id}")
    public Flux<String> listPaymentByIdAccClient(@PathVariable int id) {
//        log.info(appConfig.toString());
        return accountPaymentService.findAccountPaymentByAccClientId(id);
    }
    
    @GetMapping("/list/lento/{id}")
    public Flux<String> listPaymentByIdAccClient2(@PathVariable int id) {
        long sleep = 3000;
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        log.info(appConfig.toString());
        return accountPaymentService.findAccountPaymentByAccClientId(id);
    }

    @GetMapping("/list/dates/{productId}/{dateIni}/{dateFin}")
    public Flux<String> listPaymentByDates(@PathVariable int productId, @PathVariable String dateIni, @PathVariable String dateFin) {

        return accountPaymentService.findAccountPaymentByProductIdAndDateBetween(productId, dateIni, dateFin);
    }
    @GetMapping("/list/cardNumber/{cardNumber}")
    public Flux<String> listPaymentByCardNumber(@PathVariable String cardNumber) {

        return accountPaymentService.findAccountPaymentByCardNumber(cardNumber);
    }
}   
