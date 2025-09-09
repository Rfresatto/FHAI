package br.com.fhai.model.usuarios.contasBancaria;

import br.com.fhai.model.usuarios.contasBancaria.cartoes.Cartoes;

public class ContasBancaria {
    private String nome;
    private String numeroConta;
    private String agencia;
    private String tipoConta; // "corrente", "poupanca", "salario"
    private double saldo = 0;
    private Cartoes cartao;

    //  Contrutores
    public ContasBancaria() {
    }

    public ContasBancaria(String nome, String numeroConta, String agencia, String tipoConta) {
        this.nome = nome;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.tipoConta = tipoConta;
    }

    public ContasBancaria(String banco, String numeroConta, String agencia, String tipoConta, double saldo, Cartoes cartao) {
        this.nome = banco;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
        this.cartao = cartao;
    }

    //  Getters and Setters ... Atalho para criação padrão(IDE: Intellij): Alt + Ins
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cartoes getCartao() {
        return cartao;
    }

    public void setCartao(Cartoes cartao) {
        this.cartao = cartao;
    }

    //  Métodos
    public String exibirDados() {
        return "\n-~-~-~-~-~- Dados do Cartão -~-~-~-~-~-" +
                "\nBanco:" + this.getNome() +
                "\nNumero da conta: " + this.getNumeroConta() +
                "\nAgência: " + this.getAgencia() +
                "\nTipo da conta: " + this.getTipoConta();
    }

    public String exibirDadosCartao() {
        return exibirDados() + "\n" +
                this.getCartao().exibirDados();

    }
}
