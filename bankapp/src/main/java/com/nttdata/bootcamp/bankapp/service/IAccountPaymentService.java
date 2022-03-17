package com.nttdata.bootcamp.bankapp.service;

import com.nttdata.bootcamp.bankapp.dto.AccountPaymentDTO;
import com.nttdata.bootcamp.bankapp.model.AccountClient;
import com.nttdata.bootcamp.bankapp.model.AccountPayment;
import java.time.LocalDate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountPaymentService {

    public Mono<AccountPayment> save(AccountPayment e);

    public Mono<?> savePaymentByCardNumber(AccountPaymentDTO accountPaymentDTO);

    Mono<AccountPayment> findById(String id);

    Flux<AccountPayment> findAll();

    Mono<AccountPayment> update(AccountPayment e);

    Mono<Void> delete(String id);

    public Flux<String> findAccountPaymentByAccClientId(int id);

    public Flux<String> findAccountPaymentByProductIdAndDateBetween(int productId, String dateIni, String dateFin);
    
    public Flux<String> findAccountPaymentByCardNumber(String cardNumber);
}
