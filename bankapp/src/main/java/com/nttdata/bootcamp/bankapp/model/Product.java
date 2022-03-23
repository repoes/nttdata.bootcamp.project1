package com.nttdata.bootcamp.bankapp.model;

import java.io.Serializable;
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
public class Product implements Serializable{
	@Id
	private int id;
	private String name;
	private String producttype;
	private String productsubtype;
	private BigDecimal comission;
	private int movementsmax;
}
