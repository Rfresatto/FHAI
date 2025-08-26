package br.com.fhai.model;

import java.sql.Date;

public class Cartao {
    private String numeroCartao;
    private String bandeira;
    private String tipoCartao; // crédito, débito
    private String nomeTitular;
    private Date dataValidade;
    private String cvv;
    private double limite;
    private double faturaAtual;
    private Despesa saida;
    private Receita entrada;
    Conta conta;

//  Contrutores
    public Cartao() {

    }

    public Cartao(String tipoCartao, String bandeira, String numeroCartao, String nomeTitular, Conta conta, Date dataValidade, String cvv) {
        this.tipoCartao = tipoCartao;
        this.bandeira = bandeira;
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.conta = conta;
        this.dataValidade = dataValidade;
        this.cvv = cvv;
    }

//  Getters and Setters ... Atalho para criação padrão(IDE: Intellij): Alt + Ins
    public String getNumeroCartao() {
        return numeroCartao;
    }

    public Cartao setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
        return this;
    }

    public String getBandeira() {
        return bandeira;
    }

    public Cartao setBandeira(String bandeira) {
        this.bandeira = bandeira;
        return this;
    }

    public String getTipoCartao() {
        return tipoCartao;
    }

    public Cartao setTipoCartao(String tipoCartao) {
        this.tipoCartao = tipoCartao;
        return this;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public Cartao setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
        return this;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public Cartao setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
        return this;
    }

    public String getCvv() {
        return cvv;
    }

    public Cartao setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }

    public double getLimite() {
        return limite;
    }

    public Cartao setLimite(double limite) {
        this.limite = limite;
        return this;
    }

    public double getFaturaAtual() {
        return faturaAtual;
    }

    public Cartao setFaturaAtual(double faturaAtual) {
        this.faturaAtual = faturaAtual;
        return this;
    }

    public Despesa getSaida() {
        return saida;
    }

    public Cartao setSaida(Despesa saida) {
        this.saida = saida;
        return this;
    }

    public Receita getEntrada() {
        return entrada;
    }

    public Cartao setEntrada(Receita entrada) {
        this.entrada = entrada;
        return this;
    }

    //  Métodos
    public void sincronizarFatura() {
        System.out.println("Sincronizando fatura atual do cartão :" + numeroCartao);
    }

    public void importarTransacoesCartao(int dias) {
        System.out.println("Importando transações do cartão :" + numeroCartao);
    }

    public void visualizarLimite() {
        System.out.println("Limite do cartão " + numeroCartao + ": R$ " + limite + " | Disponível R$ " + (limite - faturaAtual));
    }

    public void visualizarFatura() {
        System.out.println("Exibindo fatura atual do cartão " + numeroCartao + ": R$ " + faturaAtual);
    }

    public void importarExtratoCartao() {
        System.out.println("Importando extrato detalhado do cartão " + numeroCartao);
    }

    public void categorizarGastosCartao() {
        System.out.println("Categorizando gastos do cartão " + numeroCartao + " automaticamente");
    }

    public void analisarPadraoGastos() {
        System.out.println("Analisando padrão de gastos do cartão " + numeroCartao);
    }
}