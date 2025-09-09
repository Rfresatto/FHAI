package br.com.fhai.model.usuario.contaBancaria.cartao;

import java.util.ArrayList;
import java.util.List;

public class Cartao {
    private String nome;
    private long numeroCartao;
    private String tipoCartao; // crédito, débito
    private String nomeTitular;
    private String dataValidade;
    private int cvv;
    private double saldo = 0;

    private List<Transacao> transacoes = new ArrayList<>();

    //  Contrutores
    public Cartao() {
    }

    public Cartao(String nome, long numeroCartao, String tipoCartao, String nomeTitular, String dataValidade, int cvv) {
        this.nome = nome;
        this.numeroCartao = numeroCartao;
        this.tipoCartao = tipoCartao;
        this.nomeTitular = nomeTitular;
        this.dataValidade = dataValidade;
        this.cvv = cvv;
    }

    //  Getters and Setters ... Atalho para criação padrão(IDE: Intellij): Alt + Ins

    public long getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(long numeroCartao) {
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

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    //  Métodos

    public String exibirDados() {
        return "\nNome do cartão: " + this.getNome() +
                "\nNúmero: " + this.getNumeroCartao() +
                "\nTipo: " + this.getTipoCartao() +
                "\nNome do Titular: " + this.getNomeTitular() +
                "\nData de validade: " + this.getDataValidade() +
                "\nCódigo de segurança: " + this.getCvv() +
                "\nSaldo: " + this.getSaldo();
    }

    public void adicionarTransacao(Transacao transacao, boolean receita) {
        if (receita) {
            this.saldo += transacao.getValor();
        } else {
            this.saldo -= transacao.getValor();
        }
        this.transacoes.add(transacao);
    }
}