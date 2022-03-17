package com.nttdata.bootcamp.bankapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class AccountPayment {
	@Id
	private String id;
	private AccountClient accountClient;
	private BigDecimal amount;
	private String movementtype;
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime date;
        
}
