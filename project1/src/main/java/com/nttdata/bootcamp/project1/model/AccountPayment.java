package com.nttdata.bootcamp.project1.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class AccountPayment {
	@Id
	private int id;
	private AccountClient accountClient;
	private BigDecimal amount;
	private String movementtype;
}
