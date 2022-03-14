package com.nttdata.bootcamp.project1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime date;
}
