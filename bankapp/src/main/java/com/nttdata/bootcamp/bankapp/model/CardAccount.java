package com.nttdata.bootcamp.bankapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Document
public class CardAccount {
	@Id
	int id;
	String cardNumber;
        @Indexed(unique=true)
	AccountClient accountClient;
        boolean accountPrincipal;
}
