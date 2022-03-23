package com.nttdata.bootcamp.bankapp;

import com.nttdata.bootcamp.bankapp.model.AccountClient;
import com.nttdata.bootcamp.bankapp.model.AccountPayment;
import com.nttdata.bootcamp.bankapp.service.IAccountPaymentService;
import com.nttdata.bootcamp.bankapp.controller.AccountPaymentController;
import com.nttdata.bootcamp.bankapp.model.Client;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import  static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.BodyInserters;

@AutoConfigureWebTestClient(timeout = "36000")
@ExtendWith(SpringExtension.class)
@WebFluxTest(AccountPaymentController.class)
@Import(IAccountPaymentService.class)
//@SpringBootTest
class BankappApplicationTests {

//	@Test
//	void contextLoads() {
//	}
@Autowired
    private WebTestClient webTestClient;

    @MockBean
    private IAccountPaymentService iAccountPaymentService;

    final LocalDateTime dateTime = LocalDateTime.parse("16/03/2022 13:00:00",DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    
    @Test
    public void saveAccountPayment() {
        
        AccountPayment mono = new AccountPayment(null, 
                new AccountClient(1,null,null,null,null,null,null), BigDecimal.TEN, "RETIRO",dateTime,new Client());
        when(iAccountPaymentService.save(mono)).thenReturn(Mono.just(mono));
        webTestClient.post().uri("/accountPayment/save")
                
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(mono))
                .exchange()
                .expectStatus().isCreated();
                
    }

    @Test
    public void getAllAccountPayment() {
        Flux<AccountPayment> flux = Flux.just(
                new AccountPayment(null, 
                    new AccountClient(1,null,null,null,null,null,null), BigDecimal.TEN, "DEPOSITO",dateTime,new Client()), 
                new AccountPayment(null, 
                    new AccountClient(1,null,null,null,null,null,null), BigDecimal.TEN, "DEPOSITO", dateTime,new Client()));
        when(iAccountPaymentService.findAll()).thenReturn(flux);
        Flux<AccountPayment> responseBody = webTestClient.get().uri("/accountPayment/list")
                .exchange()
                .expectStatus().isOk()
                .returnResult(AccountPayment.class)
                .getResponseBody();
        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new AccountPayment(null, 
                    new AccountClient(1,null,null,null,null,null,null), BigDecimal.TEN, "DEPOSITO", dateTime,new Client()))
                .expectNext(new AccountPayment(null, 
                    new AccountClient(1,null,null,null,null,null,null), BigDecimal.TEN, "DEPOSITO", dateTime,new Client()))
                .verifyComplete();
    }
    @Test
    public void getAllAccountPaymentByProductAndDates() {
        int productId = 1;
//        LocalDate localDate1 = LocalDate.parse("2022-03-01");
//        LocalDate localDate2 = LocalDate.parse("2022-03-15");
        Flux<String> flux = Flux.just("");
        when(iAccountPaymentService.findAccountPaymentByProductIdAndDateBetween(productId,"2022-03-01","2022-03-15")).thenReturn(flux);
        Flux<AccountPayment> responseBody = webTestClient.get().
                uri("/accountPayment/list/dates"+"/"+productId+"/2022-03-01/2022-03-15")
                .exchange()
                .expectStatus().isOk()
                .returnResult(AccountPayment.class)
                .getResponseBody();
        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new AccountPayment(null, 
                    new AccountClient(1,null,null,null,null,null,null), BigDecimal.TEN, "DEPOSITO", dateTime,new Client()))
                .expectNext(new AccountPayment(null, 
                    new AccountClient(1,null,null,null,null,null,null), BigDecimal.TEN, "DEPOSITO", dateTime,new Client()))
                .verifyComplete();
    }
    @Test
    public void getAllAccountPaymentByCardNumber() {
        String cardNumber = "12345";
        Flux<String> flux = Flux.just("");
        when(iAccountPaymentService.findAccountPaymentByCardNumber(cardNumber)).thenReturn(flux);
        Flux<AccountPayment> responseBody = webTestClient.get().uri("/accountPayment/list/cardNumber/"+cardNumber)
                .exchange()
                .expectStatus().isOk()
                .returnResult(AccountPayment.class)
                .getResponseBody();
//        StepVerifier.create(responseBody)
//                .expectSubscription()
//                .expectNext(new AccountPayment(null, 
//                    new AccountClient(1,null,null,null,null,null), BigDecimal.TEN, "DEPOSITO", dateTime,new Client()))
//                .expectNext(new AccountPayment(null, 
//                    new AccountClient(1,null,null,null,null,null), BigDecimal.TEN, "DEPOSITO", dateTime,new Client()))
//                .verifyComplete();
    }
}
