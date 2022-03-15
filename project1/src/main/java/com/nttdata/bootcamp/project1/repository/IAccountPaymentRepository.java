package com.nttdata.bootcamp.project1.repository;

import java.math.BigDecimal;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.project1.model.AccountClient;
import com.nttdata.bootcamp.project1.model.AccountPayment;
import com.nttdata.bootcamp.project1.model.Clienttype;
import com.nttdata.bootcamp.project1.model.Product;
import java.time.LocalDate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IAccountPaymentRepository extends ReactiveMongoRepository<AccountPayment, String>{
	public Flux<AccountPayment> findByAccountClientIdAndDateBetween(int accountClientId,LocalDate dateIni,LocalDate dateFin);
        public Flux<AccountPayment> findByAccountClientId(int accountClientId);
}
