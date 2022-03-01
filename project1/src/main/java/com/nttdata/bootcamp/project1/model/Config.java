package com.nttdata.bootcamp.project1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Config {
	@Id
	private String id;
	
	private Clienttype clienttype;
	private Producttype producttype;
	private Productsubtype productsubtype;
	private int maxAccount;
}
