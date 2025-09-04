package br.com.fhai.model.usuarios.contasBancarias.cartoes;

import br.com.fhai.model.usuarios.contasBancarias.ContasBancaria;
import br.com.fhai.model.usuarios.contasBancarias.cartoes.transacoes.Despesas;
import br.com.fhai.model.usuarios.contasBancarias.cartoes.transacoes.Receitas;

import java.sql.Date;

public class Cartao {
    private String numeroCartao;
    private String tipoCartao; // crédito, débito
    private String nomeTitular;
    private Date dataValidade;
    private String cvv;
    private double limite;
    private double faturaAtual;
    private Despesas saida;
    private Receitas entrada;

    //  Contrutores
    public Cartao() {

    }

    public Cartao(String numeroCartao, String tipoCartao, String nomeTitular, Date dataValidade, String cvv, double limite, double faturaAtual, Despesas saida, Receitas entrada) {
        this.numeroCartao = numeroCartao;
        this.tipoCartao = tipoCartao;
        this.nomeTitular = nomeTitular;
        this.dataValidade = dataValidade;
        this.cvv = cvv;
        this.limite = limite;
        this.faturaAtual = faturaAtual;
        this.saida = saida;
        this.entrada = entrada;
    }

    //  Getters and Setters ... Atalho para criação padrão(IDE: Intellij): Alt + Ins

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getTipoCartao() {
        return tipoCartao;
    }

    public void setTipoCartao(String tipoCartao) {
        this.tipoCartao = tipoCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getFaturaAtual() {
        return faturaAtual;
    }

    public void setFaturaAtual(double faturaAtual) {
        this.faturaAtual = faturaAtual;
    }

    public Despesas getSaida() {
        return saida;
    }

    public void setSaida(Despesas saida) {
        this.saida = saida;
    }

    public Receitas getEntrada() {
        return entrada;
    }

    public void setEntrada(Receitas entrada) {
        this.entrada = entrada;
    }

    //  Métodos

    public void detalhesCartão() {
        System.out.println("Exibindo detalhes do cartao " + numeroCartao + ": R$ " + faturaAtual);
    }
}