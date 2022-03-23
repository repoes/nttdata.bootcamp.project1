/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.bootcamp.bankapp.model;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author erojaalf
 */
@Data
@Document
public class CurrencyExchange {
    String currencyFrom;
    String currencyTo;
    BigDecimal sellValue;
    BigDecimal purchaseValue;
}
