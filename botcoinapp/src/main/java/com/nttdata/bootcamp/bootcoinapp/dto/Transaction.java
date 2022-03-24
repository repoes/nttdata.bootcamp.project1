/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.bootcamp.bootcoinapp.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author erojaalf
 */
@Data
public class Transaction {
    private String id;
    private int accountFromId;
    private int accountToId;
    private BigDecimal amount;
    private String currencyExchangeRateId;
    
}
