package br.com.fhai.model.usuarios.contasBancarias.cartoes.transacoes;

import java.sql.Date;
public class Receitas {
    private String nome;
    private String descricao;
    private double valor;
    private Date data;


//  Contrutores
    public Receitas(){

    }

    public Receitas(String nome, String descricao, double valor, Date data) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

//  Getters and Setters ... Atalho para criação padrão(IDE: Intellij): Alt + Ins

    public String getNome() {
        return nome;
    }

    public Receitas setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Receitas setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public double getValor() {
        return valor;
    }

    public Receitas setValor(double valor) {
        this.valor = valor;
        return this;
    }

    public Date getData() {
        return data;
    }

    public Receitas setData(Date data) {
        this.data = data;
        return this;
    }

}
