/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.bootcamp.bankapp.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author erojaalf
 */
@Data
@Document
public class CurrencyExchange {
    @Id
    private String id;
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal sellValue;
    private BigDecimal purchaseValue;
    private String day;
}
