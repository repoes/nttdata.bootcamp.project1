package com.nttdata.bootcamp.project1;

import com.nttdata.bootcamp.project1.model.AccountClient;
import com.nttdata.bootcamp.project1.model.AccountPayment;
import com.nttdata.bootcamp.project1.service.IAccountPaymentService;
import com.nttdata.bootcamp.project1.controller.AccountPaymentController;
import com.nttdata.bootcamp.project1.service.impl.AccountPaymentServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import  static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(AccountPaymentController.class)
//@SpringBootTest
class Project1ApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private IAccountPaymentService iAccountPaymentService;

    @Test
    public void saveAccountPayment() {
        AccountPayment mono = new AccountPayment(null, 
                new AccountClient(1,null,null,null,null,null,false), BigDecimal.TEN, "DEPOSITO", LocalDateTime.MAX);
        Mockito.when(iAccountPaymentService.save(mono)).thenReturn(Mono.just(mono));
        webTestClient.post().uri("/accountPayment/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(mono), AccountPayment.class)
                .exchange()
                .expectStatus().isCreated();

    }

    @Test
    public void getAllAccountPayment() {
        Flux<AccountPayment> flux = Flux.just(
                new AccountPayment(null, 
                    new AccountClient(1,null,null,null,null,null,false), BigDecimal.TEN, "DEPOSITO", LocalDateTime.MAX), 
                new AccountPayment(null, 
                    new AccountClient(1,null,null,null,null,null,false), BigDecimal.TEN, "DEPOSITO", LocalDateTime.MAX));
        when(iAccountPaymentService.findAll()).thenReturn(flux);
        Flux<AccountPayment> responseBody = webTestClient.get().uri("/accountPayment/list")
                .exchange()
                .expectStatus().isOk()
                .returnResult(AccountPayment.class)
                .getResponseBody();
        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new AccountPayment(null, 
                    new AccountClient(1,null,null,null,null,null,false), BigDecimal.TEN, "DEPOSITO", LocalDateTime.MAX))
                .expectNext(new AccountPayment(null, 
                    new AccountClient(1,null,null,null,null,null,false), BigDecimal.TEN, "DEPOSITO", LocalDateTime.MAX))
                .verifyComplete();
    }
    @Test
    public void getAllAccountPaymentByProductAndDates() {
        int productId = 1;
//        LocalDate localDate1 = LocalDate.parse("2022-03-01");
//        LocalDate localDate2 = LocalDate.parse("2022-03-15");
        Flux<String> flux = Flux.just("");
        when(iAccountPaymentService.findAccountPaymentByProductIdAndDateBetween(productId,"2022-03-01","2022-03-15")).thenReturn(flux);
        Flux<AccountPayment> responseBody = webTestClient.get().uri("/accountPayment/list/dates")
                .exchange()
                .expectStatus().isOk()
                .returnResult(AccountPayment.class)
                .getResponseBody();
        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new AccountPayment(null, 
                    new AccountClient(1,null,null,null,null,null,false), BigDecimal.TEN, "DEPOSITO", LocalDateTime.MAX))
                .expectNext(new AccountPayment(null, 
                    new AccountClient(1,null,null,null,null,null,false), BigDecimal.TEN, "DEPOSITO", LocalDateTime.MAX))
                .verifyComplete();
    }
    @Test
    public void getAllAccountPaymentByCardNumber() {
        String cardNumber = "123";
        Flux<String> flux = Flux.just("");
        when(iAccountPaymentService.findAccountPaymentByCardNumber(cardNumber)).thenReturn(flux);
        Flux<AccountPayment> responseBody = webTestClient.get().uri("/accountPayment/list/cardNumber")
                .exchange()
                .expectStatus().isOk()
                .returnResult(AccountPayment.class)
                .getResponseBody();
        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new AccountPayment(null, 
                    new AccountClient(1,null,null,null,null,null,false), BigDecimal.TEN, "DEPOSITO", LocalDateTime.MAX))
                .expectNext(new AccountPayment(null, 
                    new AccountClient(1,null,null,null,null,null,false), BigDecimal.TEN, "DEPOSITO", LocalDateTime.MAX))
                .verifyComplete();
    }
}
