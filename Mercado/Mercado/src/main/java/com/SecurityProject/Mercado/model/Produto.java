package com.SecurityProject.Mercado.model;

/**
 * Representa um produto dentro do sistema de mercado.
 * Esta classe contém informações básicas como identificador,
 * nome e preço do produto.
 */
public class Produto {

    private Long id;
    private String nome;
    private double preco;

    /**
     * Construtor completo para criar um produto com todos os atributos.
     *
     * @param id    identificador único do produto
     * @param nome  nome do produto
     * @param preco preço do produto
     */
    public Produto(Long id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    /**
     * Construtor padrão, utilizado principalmente em processos
     * de desserialização (ex.: JSON para objeto).
     */
    public Produto() {
    }

    /**
     * Obtém o identificador do produto.
     *
     * @return id do produto
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtém o nome do produto.
     *
     * @return nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o preço do produto.
     *
     * @return preço do produto
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o identificador do produto.
     *
     * @param id novo identificador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome novo nome do produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco novo preço do produto
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }
}
