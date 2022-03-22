/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.bootcamp.bankapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author erojaalf
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "<h2>Bank App is working</h2>"
                + "<a href='swagger-ui.html'>Documentación</a>";
    }
}
