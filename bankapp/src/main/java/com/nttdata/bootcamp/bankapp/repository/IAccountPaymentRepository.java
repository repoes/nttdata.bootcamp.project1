package com.nttdata.bootcamp.bankapp.repository;

import java.math.BigDecimal;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.bankapp.model.AccountClient;
import com.nttdata.bootcamp.bankapp.model.AccountPayment;
import com.nttdata.bootcamp.bankapp.model.Clienttype;
import com.nttdata.bootcamp.bankapp.model.Product;
import java.time.LocalDate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IAccountPaymentRepository extends ReactiveMongoRepository<AccountPayment, String>{
	public Flux<AccountPayment> findByAccountClientIdAndDateBetween(int accountClientId,LocalDate dateIni,LocalDate dateFin);
        public Flux<AccountPayment> findByAccountClientId(int accountClientId);
}
