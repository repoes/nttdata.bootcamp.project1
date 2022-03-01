package com.nttdata.bootcamp.project1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Product {
	@Id
	private int id;
	private String name;
	private String producttype;
	private String productsubtype;
}
