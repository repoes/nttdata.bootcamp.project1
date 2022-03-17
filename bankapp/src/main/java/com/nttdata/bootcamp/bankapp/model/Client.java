package com.nttdata.bootcamp.bankapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Client {
	@Id
	private int id;
	private Clienttype clienttype;
	private String name;
}
