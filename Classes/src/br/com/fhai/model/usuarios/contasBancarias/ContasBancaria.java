package br.com.fhai.model.usuarios.contasBancarias;

import br.com.fhai.model.usuarios.contasBancarias.cartoes.Cartao;

public class ContasBancaria {
    private String banco;
    private String numeroConta;
    private String agencia;
    private String tipoConta; // "corrente", "poupanca", "salario"
    private double saldo;
    private Cartao cartao;

    //  Contrutores
    public ContasBancaria() {
    }

    public ContasBancaria(String banco, String numeroConta, String agencia, String tipoConta, double saldo, Cartao cartao) {
        this.banco = banco;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
        this.cartao = cartao;
    }

    //  Getters and Setters ... Atalho para criação padrão(IDE: Intellij): Alt + Ins
    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
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

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    //  Métodos
    public String visualizarSaldo() {
        return "Exibindo saldo atual da conta " +
                this.numeroConta + ": R$ " +
                this.saldo;
    }
}
