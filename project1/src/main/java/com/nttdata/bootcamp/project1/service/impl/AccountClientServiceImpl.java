package com.nttdata.bootcamp.project1.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.project1.model.Account;
import com.nttdata.bootcamp.project1.model.AccountClient;
import com.nttdata.bootcamp.project1.repository.IAccountClientRepository;
import com.nttdata.bootcamp.project1.service.IAccountClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountClientServiceImpl implements IAccountClientService {

	@Autowired
	IAccountClientRepository iAccountClientRepository;

	@Override
	public void save(AccountClient e) throws Exception {
//		try {
//			List<AccountClient> listAccountsByClient = this.findByClientId(e.getClient().getId()).collectList().block();
			
//			listAccountsByClient.forEach( data -> {
//				if (e.getClient().getClienttype().getId() == 1
//						&& (data.getProduct().getProducttype().getId() == 1
//								|| data.getProduct().getProducttype().getId() == 2
//								|| data.getProduct().getProducttype().getId() == 3)) {
//					throw new Exception("El cliente de tipo personal no puede tener mas de una cuenta de ahorro, corriente o de plazo fijo");
//				}
//				if (e.getClient().getClienttype().getId() == 2 && 
//						(e.getProduct().getProducttype().getId() == 1 || e.getProduct().getProducttype().getId() == 1)) {
//					throw new Exception("El cliente de tipo empresarial no puede tener mas de una cuenta de ahorro, corriente o de plazo fijo");
//				}
//			});
//			List<AccountClient> list0 = listAccountsByClient.stream()
//			  .filter(c -> e.getClient().getClienttype().getId() == 1
//						&& (c.getProduct().getProductsubtype().getId() == 1
//						|| c.getProduct().getProductsubtype().getId() == 2
//						|| c.getProduct().getProductsubtype().getId() == 3))
//			  .collect(Collectors.toList());
//			if(list0.size() > 0) {
//				throw new Exception("El cliente de tipo personal no puede tener mas de una cuenta de ahorro, corriente o de plazo fijo");
//			}
//			
//			List<AccountClient> list1 = listAccountsByClient.stream()
//					  .filter(c -> e.getClient().getClienttype().getId() == 2 && 
//								(c.getProduct().getProductsubtype().getId() == 1 || c.getProduct().getProductsubtype().getId() == 3))
//					  .collect(Collectors.toList());
//			if(list1.size() > 0) {
//				throw new Exception("El cliente de tipo empresarial no puede tener mas de una cuenta de ahorro, corriente o de plazo fijo");
//			}
//			List<AccountClient> list2 = listAccountsByClient.stream()
//					  .filter(c -> c.getProduct().getProductsubtype().getId() == 2 && (c.getProduct().getProducttype().getId() == 1 || c.getProduct().getProducttype().getId() == 1))
//					  .collect(Collectors.toList());
//			if(list1.size() > 0) {
//				throw new Exception("El cliente de tipo personal no puede tener mas de una cuenta de ahorro, corriente o de plazo fijo");
//			}
			
//			Flux<AccountClient> listAccountsByClient2 = findByClientId(e.getClient().getId());
//				listAccountsByClient2.subscribe(data -> {
//					if (e.getClient().getClienttype().getId() == 1
//							&& (data.getProduct().getProducttype().getId() == 1
//									|| data.getProduct().getProducttype().getId() == 2
//									|| data.getProduct().getProducttype().getId() == 3)) {
//						throw new RuntimeException("El cliente de tipo personal no puede tener mas de una cuenta de ahorro, corriente o de plazo fijo");
//					}
//					if (e.getClient().getClienttype().getId() == 2 && 
//							(e.getProduct().getProducttype().getId() == 1 || e.getProduct().getProducttype().getId() == 3)) {
//						throw new RuntimeException("El cliente de tipo empresarial no puede tener mas de una cuenta de ahorro, corriente o de plazo fijo");
//					}
//				});
				
//			listAccountsByClient.count();
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}
		
		findByClientId(e.getClient().getId())
		.filter(data -> {
			if(e.getClient().getClienttype().getId() == 1) {
				if(data.getProduct().getProductsubtype().getId() == e.getProduct().getProductsubtype().getId()) {
					throw new RuntimeException("El cliente de tipo personal no puede tener mas de una cuenta de ahorro, "
							+ "corriente o de plazo fijo");
				}
			}
			return false;
		}).filter(data -> {
			if(e.getClient().getClienttype().getId() == 2) {
				if(data.getProduct().getProducttype().getId() == 1
						|| data.getProduct().getProducttype().getId() == 3) {
					throw new RuntimeException("El cliente de tipo personal no puede tener mas de una cuenta de ahorro, "
							+ "corriente o de plazo fijo");
				}
			}
			return false;
		});
		iAccountClientRepository.save(e);
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
		return iAccountClientRepository.save(e);
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
