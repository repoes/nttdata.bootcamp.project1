package com.nttdata.bootcamp.project1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.Nullable;

import lombok.Data;

@Data
@Document
public class Productsubtype {
	@Id
	private int id;
	private String name;
	private Producttype producttype;
	private int comission;
	
	@Nullable
	private int movements;
}
