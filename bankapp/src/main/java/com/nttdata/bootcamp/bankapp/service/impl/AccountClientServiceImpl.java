package com.nttdata.bootcamp.bankapp.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.bankapp.controller.AccountClientController;
import com.nttdata.bootcamp.bankapp.model.AccountClient;
import com.nttdata.bootcamp.bankapp.model.Client;
import com.nttdata.bootcamp.bankapp.repository.IAccountClientRepository;
import com.nttdata.bootcamp.bankapp.repository.IClientRepository;
import com.nttdata.bootcamp.bankapp.repository.IProductRepository;
import com.nttdata.bootcamp.bankapp.service.IAccountClientService;
import com.nttdata.bootcamp.bankapp.util.Constants;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountClientServiceImpl implements IAccountClientService {

    private static final Logger log = LoggerFactory.getLogger(AccountClientServiceImpl.class);

    @Autowired
    IAccountClientRepository iAccountClientRepository;

    @Autowired
    IClientRepository iClientRepository;

    @Autowired
    IProductRepository iProductRepository;

    @Override
    public Mono<AccountClient> save(AccountClient e) throws RuntimeException {

        Flux<AccountClient> flux = findByClientId(e.getClient().getId())
                .map(data -> {
                    if (e.getClient() == null) {
                        throw new RuntimeException("Debe indicar el cliente");
                    }
                    if (e.getProduct() == null) {
                        throw new RuntimeException("Debe indicar el producto");
                    }
                    if (e.getBaseamount() == null) {
                        throw new RuntimeException("Debe indicar el monto base");
                    }
                    if (e.getAmount() == null) {
                        throw new RuntimeException("Debe indicar el monto");
                    }
                    return data;
                })
                .map( data -> {
        			//tienes un flujo del clienta a con sus cuentas
        			//te llega un cliente con una nueva cuenta
        			//filtrar sus productos de credito y verificar si la fecha ya vencio
        			// si vencio error
        			// sino dejar fluir
                	System.out.println("const "+Constants.PRODUCTO_CREDITO);
                	System.out.println("data "+data.getProduct().getProducttype());
                	System.out.println("logica 1 :"+data.getProduct().getProducttype().equals(Constants.PRODUCTO_CREDITO ));
                	System.out.println("logica 2:"+data.getProduct().getProducttype() == Constants.PRODUCTO_CREDITO);
        			if( data.getProduct().getProducttype().equals(Constants.PRODUCTO_CREDITO) ) {
        				System.out.println("entre al producto de creidto");
        				//String formate = "2015-02-01";
        				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        		        LocalDate dateActual = LocalDate.now();
        		        LocalDate dateExp = LocalDate.parse(data.getDateExp(), formatter);
        		        System.out.println("fecha actual"+dateActual);
        		        System.out.println("fecha expiracion"+dateExp);
        		        System.out.println("comparacion fechas"+dateActual.isAfter(dateExp));
        			    if(dateActual.isAfter(dateExp)) {
        			    	throw new RuntimeException("Tiene una cuenta de credito vencida");
        			    }
        			}
        			return data;
        		})
                .filter(data -> {
                    if (data.getClient().getClienttype().getId() == Constants.CLIENTE_TIPO_PERSONAL_ID) {
                        if (data.getProduct().getId() == e.getProduct().getId()
                                && (data.getProduct().getId() == Constants.CUENTA_AHORROS_ID
                                || data.getProduct().getId() == Constants.CUENTA_CORRIENTE_ID
                                || data.getProduct().getId() == Constants.CUENTA_PLAZO_FIJO_ID)) {
                            throw new RuntimeException("El cliente de tipo PERSONAL no puede tener mas de una cuenta de ahorro, "
                                    + "corriente o de plazo fijo");
                        }
                    }
                    return false;
                }).filter(data -> {
            if (data.getClient().getClienttype().getId() == Constants.CLIENTE_TIPO_EMPRESARIAL_ID) {
                if (data.getProduct().getId() == e.getProduct().getId()
                        && (data.getProduct().getId() == Constants.CUENTA_AHORROS_ID
                        || data.getProduct().getId() == Constants.CUENTA_PLAZO_FIJO_ID)) {
                    throw new RuntimeException("El cliente de tipo EMPRESARIAL no puede tener mas de una cuenta de ahorro o de plazo fijo");
                }
            }
            return false;
        }).switchIfEmpty(postSave(e));
        return Mono.from(flux);
    }

    private Mono<AccountClient> postSave(AccountClient e) {
        return iClientRepository.findById(e.getClient().getId())
                .flatMap(data -> {
                    log.info(data.toString());
                    e.setClient(data);
                    return iProductRepository.findById(e.getProduct().getId());
                })
                .flatMap(data2 -> {
                    log.info(data2.toString());
                    e.setProduct(data2);
                    return iAccountClientRepository.save(e);
                });
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
    public Flux<String> findByClient(int clientId) {
        return iAccountClientRepository.findAll()
                .filter(data -> data.getClient().getId() == clientId)
                .map(clienttype -> {
                    return clienttype.getProduct().getName()
                            + "(monto base " + clienttype.getBaseamount() + "): "
                            + clienttype.getAmount() + "\n";
                });
    }

    @Override
    public Mono<AccountClient> update(AccountClient e) {

        Flux<AccountClient> finded = iAccountClientRepository.findByClientId(e.getId()).map(data -> {
            return data;
        });
        finded.map(null);
        return finded.collectList().map(data -> {
            if (data.size() >= 0) {
                iAccountClientRepository.save(null);
                return new AccountClient();
            } else {
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
