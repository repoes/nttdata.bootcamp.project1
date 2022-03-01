package com.nttdata.bootcamp.project1.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.project1.controller.AccountClientController;
import com.nttdata.bootcamp.project1.model.Account;
import com.nttdata.bootcamp.project1.model.AccountClient;
import com.nttdata.bootcamp.project1.repository.IAccountClientRepository;
import com.nttdata.bootcamp.project1.service.IAccountClientService;
import com.nttdata.bootcamp.project1.util.Constants;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountClientServiceImpl implements IAccountClientService {

	private static final Logger log = LoggerFactory.getLogger(AccountClientServiceImpl.class);
	
	@Autowired
	IAccountClientRepository iAccountClientRepository;

	@Override
	public void save(AccountClient e) throws RuntimeException {
		
		Flux<AccountClient> list = findByClientId(e.getClient().getId())
		
		.filter(data -> {
			
			if(e.getClient().getClienttype().getId() == Constants.CLIENTE_TIPO_PERSONAL_ID) {
				if(data.getProduct().getId() == e.getProduct().getId() 
						&& (data.getProduct().getId() == Constants.CUENTA_AHORROS_ID
							|| data.getProduct().getId() == Constants.CUENTA_CORRIENTE_ID
							|| data.getProduct().getId() == Constants.CUENTA_PLAZO_FIJO_ID)) {
					throw new RuntimeException("El cliente de tipo PERSONAL no puede tener mas de una cuenta de ahorro, "
							+ "corriente o de plazo fijo");
				}
			}
			return true;
		}).filter(data -> {
			if(e.getClient().getClienttype().getId() == Constants.CLIENTE_TIPO_EMPRESARIAL_ID) {
				if(data.getProduct().getId() == e.getProduct().getId() 
						&& (data.getProduct().getId() == Constants.CUENTA_AHORROS_ID
						|| data.getProduct().getId() == Constants.CUENTA_PLAZO_FIJO_ID)) {
					throw new RuntimeException("El cliente de tipo EMPRESARIAL no puede tener mas de una cuenta de ahorro o de plazo fijo");
				}
			}
			return true;
		});
		//cambiar aqui, si es vacío no graba
//		list.subscribe(data -> {
			iAccountClientRepository.save(e).subscribe(data2 ->{
				log.info("guardado!: " + data2.toString());
			});
//		});
		
	}

	@Override
	public Mono<AccountClient> findById(Integer id) {
		return iAccountClientRepository.findById(id);
	}

	@Override
	public Flux<AccountClient> findAll() {
		return iAccountClientRepository.findAll();
	}

	@Override
	public Mono<AccountClient> update(AccountClient e) {
		
		Flux<AccountClient> finded = iAccountClientRepository.findByClientId(e.getId()).map(data->{
			return data;
		});
		finded.map(null);
		return finded.collectList().map(data -> {
			if(data.size() >= 0) {
				iAccountClientRepository.save(null);
				return new AccountClient();
			}else {
				return new AccountClient();
			}
		});
	}

	@Override
	public Mono<Void> delete(Integer id) {
		return iAccountClientRepository.deleteById(id);
	}

	@Override
	public Flux<AccountClient> findByClientId(int id) {
		return iAccountClientRepository.findByClientId(id);
	}

}
