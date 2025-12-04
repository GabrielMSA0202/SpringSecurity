package com.SecurityProject.Mercado.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável por lidar com as requisições relacionadas aos produtos.
 * Este controlador demonstra endpoints públicos, privados para usuários e
 * restritos para administradores.
 */
@RestController
public class ProdutoController {

    /**
     * Endpoint de acesso público, disponível sem autenticação.
     *
     * @return Mensagem indicando que o acesso é liberado.
     */
    @GetMapping("/")
    public String publico() {
        return "Acesso público liberado";
    }

    /**
     * Endpoint acessível por usuários autenticados com as roles USER ou ADMIN.
     *
     * @return Texto representando a lista de produtos.
     */
    @GetMapping("/produtos")
    public String listarProdutos() {
        return "Lista de produtos (USER e ADMIN)";
    }

    /**
     * Endpoint permitido apenas para usuários com a role ADMIN.
     *
     * @return Mensagem indicando que a área de cadastro é restrita ao ADMIN.
     */
    @PostMapping("/produtos/cadastrar")
    public String cadastrarProduto() {
        return "Área de cadastro (SOMENTE ADMIN)";
    }
}
