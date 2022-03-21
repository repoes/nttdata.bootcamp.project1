package com.nttdata.bootcamp.bankapp.repository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bootcamp.bankapp.model.AccountPayment;
import java.time.LocalDate;

import reactor.core.publisher.Flux;

@Repository
public interface IAccountPaymentRepository extends ReactiveMongoRepository<AccountPayment, String>{
	public Flux<AccountPayment> findByAccountClientIdAndDateBetween(int accountClientId,LocalDate dateIni,LocalDate dateFin);
        public Flux<AccountPayment> findByAccountClientId(int accountClientId);
}
