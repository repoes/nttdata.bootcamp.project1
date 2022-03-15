package com.nttdata.bootcamp.project1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class TargetAccount {
	@Id
	int id;
	String targetNumber;
	AccountClient accountClient;
}
