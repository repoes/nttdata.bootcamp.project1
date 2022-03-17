package com.nttdata.bootcamp.bankapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Document
public class Clienttype {
	@Id
	private int id;
	private String name;
	
}
