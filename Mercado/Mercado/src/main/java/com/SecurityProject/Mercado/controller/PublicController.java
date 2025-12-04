package com.SecurityProject.Mercado.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável por disponibilizar endpoints públicos,
 * acessíveis sem autenticação. Ideal para rotas abertas, como páginas
 * informativas ou status do sistema.
 */
@RestController
public class PublicController {

    /**
     * Endpoint público, acessível sem login. Retorna uma mensagem simples
     * indicando que o sistema é acessível a qualquer usuário.
     *
     * @return Mensagem informativa de acesso público.
     */
    @GetMapping("/publico")
    public String publico() {
        return "Sistema de Mercado - acesso público";
    }
}
