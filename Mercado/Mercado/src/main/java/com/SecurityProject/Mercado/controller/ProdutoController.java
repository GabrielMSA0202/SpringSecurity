package com.SecurityProject.Mercado.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoController {

    @GetMapping("/")
    public String publico() {
        return "Acesso público liberado";
    }

    @GetMapping("/produtos")
    public String listarProdutos() {
        return "Lista de produtos (USER e ADMIN)";
    }

    @GetMapping("/produtos/cadastrar")
    public String cadastrarProduto() {
        return "Área de cadastro (SOMENTE ADMIN)";
    }
}
