package com.nttdata.bootcamp.bankapp.model;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Client implements Serializable {
	@Id
	private int id;
	private Clienttype clienttype;
	private String name;
        private String numDoc;
        private String cellPhone;
        private String email;
}
