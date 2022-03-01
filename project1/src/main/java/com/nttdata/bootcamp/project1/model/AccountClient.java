package com.nttdata.bootcamp.project1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class AccountClient {
	@Id
	private int id;
	//private Account account;
	private Client client;
	private Product product;
}
