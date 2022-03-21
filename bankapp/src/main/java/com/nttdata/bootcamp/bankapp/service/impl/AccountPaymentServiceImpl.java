package com.nttdata.bootcamp.bankapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.bankapp.dto.AccountPaymentDTO;
import com.nttdata.bootcamp.bankapp.model.AccountClient;
import com.nttdata.bootcamp.bankapp.model.AccountPayment;
import com.nttdata.bootcamp.bankapp.repository.IAccountClientRepository;
import com.nttdata.bootcamp.bankapp.repository.IAccountPaymentRepository;
import com.nttdata.bootcamp.bankapp.repository.ICardAccountRepository;
import com.nttdata.bootcamp.bankapp.repository.IClientRepository;
import com.nttdata.bootcamp.bankapp.service.IAccountPaymentService;
import com.nttdata.bootcamp.bankapp.util.Constants;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountPaymentServiceImpl implements IAccountPaymentService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountClientServiceImpl.class);

    @Autowired
    IAccountPaymentRepository iAccountPaymentRepository;

    @Autowired
    IAccountClientRepository iAccountClientRepository;

    @Autowired
    ICardAccountRepository iCardAccountRepository;
    
    @Autowired
    IClientRepository iClientRepository;

//	@Autowired
//	private AppConfig appConfig; 
    @Override
    public Mono<?> save(AccountPayment e) {
        //buscar cuenta por accountclient.id
        //si no encuentra error
        //actualizar monto accountcliente
        //guardar pago
        return iAccountClientRepository.findById(e.getAccountClient().getId())
        .map(data -> {
            if (e.getMovementtype().equals(Constants.MOVEMENTYPE_DEPOSITO)) {
                //if id exter = interno  -> e entrante data accountclient del pagado
                if (data.getProduct().getProducttype().equals(Constants.PRODUCTO_CREDITO)) {
                    if (e.getClientPayment().getId() != data.getClient().getId()) {
                        System.out.println("un tercerro esta pagando");
                        //verificamos que el cliente exista
                        iClientRepository.findById(e.getClientPayment().getId())
                                .flatMap(client ->{
                                    //existe el cliente
                                    BigDecimal deuda = data.getBaseamount().subtract(data.getAmount());
                                    System.out.println("entre al map");
                                    System.out.println(deuda.compareTo(e.getAmount()));
                                    if( deuda.compareTo(e.getAmount()) >= 0 ){
                                        data.setAmount(data.getAmount().add(e.getAmount()));
                                    }else{
                                        throw new RuntimeException("Excediste el monto base");
                                    }
                                    return Mono.just(client);
                                });
                    }
                    //caso donde uno mismo paga su credito
                } else {
                    data.setAmount(data.getAmount().add(e.getAmount()));
                }
            } else if (e.getMovementtype().equals(Constants.MOVEMENTYPE_RETIRO)) {
                if (data.getAmount().compareTo(e.getAmount()) == -1) {
                    throw new RuntimeException("No cuenta con saldo suficiente en su cuenta");
                }
                data.setAmount(data.getAmount().subtract(e.getAmount()));
            } else {
                throw new RuntimeException("Solo se permiten los valores DEPOSITO/RETIRO");
            }
            e.setAccountClient(data);
//            e.setId(sequenceGeneratorService.generateSequence(AccountPayment.SEQUENCE_NAME));
//            iAccountPaymentRepository.save(e).subscribe();
            iAccountClientRepository.save(data).subscribe();
            return data;
        })
        .switchIfEmpty(Mono.error(new Exception("No se encuentra la cuenta")))
        .flatMap(data2 -> iAccountPaymentRepository.save(e));
    }
    
    @Override
    public Mono<?> test(AccountPayment e){
        return iAccountClientRepository.findById(e.getClientPayment().getId())
                                .map(acc -> {
                                    System.out.println("Encontre al cliente pagador");
                                    System.out.println("compara"+ acc.getAmount().compareTo(e.getAmount()));
                                    if (acc.getAmount().compareTo(e.getAmount()) >= 0) {
                                        //disminuimos al cliente pagante
                                        System.out.println("disminuimos al tercero");
                                        acc.setAmount(acc.getAmount().subtract(e.getAmount()));
                                        iAccountClientRepository.save(acc).subscribe();
                                        //reiniciamos el credito deudor
                                        System.out.println("aumentamos al pagado al pagado");
                                        acc.setAmount(acc.getAmount().add(e.getAmount()));
                                    } else {
                                        throw new RuntimeException("No cuenta con saldo suficiente en su cuenta");
                                    }
                                    return acc;
                                });
    }
    @Override
    public Mono<?> savePaymentByCardNumber(AccountPaymentDTO accountPaymentDTO) {
        return iCardAccountRepository.findByCardNumberAndAccountPrincipal(accountPaymentDTO.getCardNumber(), true)
                .switchIfEmpty(Mono.error(new Exception("No se encuentra la cuenta o no es una cuenta principal")))
                .map(data -> {
                    if (accountPaymentDTO.getMovementtype().equals(Constants.MOVEMENTYPE_DEPOSITO)) {
                        data.getAccountClient().setAmount(data.getAccountClient().getAmount().add(accountPaymentDTO.getAmount()));
                    } else if (accountPaymentDTO.getMovementtype().equals(Constants.MOVEMENTYPE_RETIRO)) {
                        if (data.getAccountClient().getAmount().compareTo(accountPaymentDTO.getAmount()) == -1) {
                            throw new RuntimeException("No cuenta con saldo suficiente en su cuenta");
                        }
                        data.getAccountClient().setAmount(data.getAccountClient().getAmount().subtract(accountPaymentDTO.getAmount()));
                    } else {
                        throw new RuntimeException("Solo se permiten los valores DEPOSITO/RETIRO");
                    }
                    AccountPayment acc = new AccountPayment();
                    acc.setAccountClient(data.getAccountClient());
                    acc.setAmount(accountPaymentDTO.getAmount());
                    acc.setMovementtype(accountPaymentDTO.getMovementtype());
                    acc.setDate(accountPaymentDTO.getDate());
                    iAccountPaymentRepository.save(acc).subscribe();
                    return data;
                })
                .flatMap(data2 -> iAccountClientRepository.save(data2.getAccountClient()));
    }

    public Flux<String> findAccountPaymentByAccClientId(int id) {
        //encontrar los AccountPayment por id del AccountClient
        return iAccountPaymentRepository.findAll()
                .filter(payment -> payment.getAccountClient().getId() == id)
                .map(pay -> {
                    return "" + pay.getAmount() + " - " + pay.getMovementtype() + " - " + "80" + "\n";
                })
                .defaultIfEmpty("No hay AccountPayment vacios");
    }

    @Override
    public Mono<AccountPayment> findById(String id) {
        return iAccountPaymentRepository.findById(id);
    }

    @Override
    public Flux<AccountPayment> findAll() {
        return iAccountPaymentRepository.findAll();
    }

    @Override
    public Mono<AccountPayment> update(AccountPayment e) {
        return iAccountPaymentRepository.save(e);
    }

    @Override
    public Mono<Void> delete(String id) {
        return iAccountPaymentRepository.deleteById(id);
    }

    @Override
    public Flux<String> findAccountPaymentByProductIdAndDateBetween(int productId, String dateIni, String dateFin) {
        return iAccountClientRepository.findByProductId(productId)
                .flatMap(data -> {
                    LocalDate localDate1 = LocalDate.parse(dateIni);
                    LocalDate localDate2 = LocalDate.parse(dateFin);
                    LOG.info(data.toString());
                    return iAccountPaymentRepository.findByAccountClientIdAndDateBetween(data.getClient().getId(), localDate1, localDate2);
                })
                .map(data2 -> {
                    LOG.info(data2.toString());
                    return data2.getAccountClient().getClient().getName() + " " + data2.getDate() + " " + data2.getAmount() + " " + data2.getMovementtype() + "\n";
                })
                .onErrorResume(ex -> {
                    LOG.info(ex.toString());
                    return Flux.just(ex.getMessage());
                }).defaultIfEmpty("No se encontraron registros");
    }

    @Override
    public Flux<String> findAccountPaymentByCardNumber(String cardNumber) {
        return iCardAccountRepository.findByCardNumberAndAccountPrincipal(cardNumber, true)
                .flatMapMany(data -> {
                    LOG.info(data.toString());
                    return iAccountPaymentRepository.findByAccountClientId(data.getAccountClient().getClient().getId())
                            .sort((obj1, obj2) -> obj2.getDate().compareTo(obj1.getDate())).take(10);
                })
                .map(data2 -> {
                    LOG.info(data2.toString());
                    return data2.getAccountClient().getClient().getName() + " " + data2.getDate() + " " + data2.getAmount() + " " + data2.getMovementtype() + "\n";
                })
                .onErrorResume(ex -> {
                    LOG.info(ex.toString());
                    return Flux.just(ex.getMessage());
                }).defaultIfEmpty("No se encontraron registros");
    }
}
