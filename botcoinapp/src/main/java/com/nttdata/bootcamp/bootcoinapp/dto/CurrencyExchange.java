/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.bootcamp.bootcoinapp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author erojaalf
 */
@Data
public class CurrencyExchange {
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal sellValue;
    private BigDecimal purchaseValue;
    private LocalDate day;
}
