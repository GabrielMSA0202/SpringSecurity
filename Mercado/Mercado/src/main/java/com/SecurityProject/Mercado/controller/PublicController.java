package com.SecurityProject.Mercado.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/publico")
    public String publico() {
        return "Sistema de Mercado - acesso público";
    }
}
