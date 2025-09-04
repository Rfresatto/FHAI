package br.com.fhai.model.usuarios.contasBancarias.cartoes.transacoes;

import java.sql.Date;

public class Despesas {
    private String nome;
    private String descricao;
    private double valor;
    private Date data;

    //  Contrutores
    public Despesas() {

    }

    public Despesas(String nome, String descricao, double valor, Date data) {

        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    //  Getters and Setters ... Atalho para criação padrão(IDE: Intellij): Alt + Ins

    public String getNome() {
        return nome;
    }

    public Despesas setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Despesas setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public double getDespesa() {
        return valor;
    }

    public Despesas setDespesa(double valor) {
        this.valor = valor;
        return this;
    }

    public Date getData() {
        return data;
    }

    public Despesas setData(Date data) {
        this.data = data;
        return this;
    }

}

