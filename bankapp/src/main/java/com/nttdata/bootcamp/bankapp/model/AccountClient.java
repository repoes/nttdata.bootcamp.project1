package com.nttdata.bootcamp.bankapp.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class AccountClient {
	@Id
	private int id;
	//private Account account;
	private Client client;
	private Product product;
	private BigDecimal amount;
	private BigDecimal baseamount;
	private String dateExp;
        private String currency;
//	private String cardNumber;
//	private boolean toCardPrincipal;
}
