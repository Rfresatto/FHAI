package br.com.fhai.model.usuarios.contasBancaria.cartoes;

public class Transacoes {

    private String nome;
    private String descricao;
    private double valor;
    private String data;

    //  Contrutores
    public Transacoes() {
    }

    public Transacoes(String nome, String descricao, double valor, String data) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    //  Getters and Setters ... Atalho para criação padrão(IDE: Intellij): Alt + Ins
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}