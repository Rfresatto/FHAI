package br.com.fhai.model;

public class Conta {
    private String banco;
    private String numeroConta;
    private String agencia;
    private String tipoConta; // "corrente", "poupanca", "salario"
    private double saldo;
    private String cpfTitular;
    Cartao cartao;

//  Contrutores
    public Conta() {
    }

    public Conta(String banco, String numeroConta, String agencia, String tipoConta, String cpfTitular) {
        this.banco = banco;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.tipoConta = tipoConta;
        this.cpfTitular = cpfTitular;
    }

//  Getters and Setters ... Atalho para criação padrão(IDE: Intellij): Alt + Ins
    public String getBanco() {
        return banco;
    }

    public Conta setBanco(String banco) {
        this.banco = banco;
        return this;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public Conta setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
        return this;
    }

    public String getAgencia() {
        return agencia;
    }

    public Conta setAgencia(String agencia) {
        this.agencia = agencia;
        return this;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public Conta setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
        return this;
    }

    public double getSaldo() {
        return saldo;
    }

    public Conta setSaldo(double saldo) {
        this.saldo = saldo;
        return this;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public Conta setCpfTitular(String cpfTitular) {
        this.cpfTitular = cpfTitular;
        return this;
    }

//  Métodos
    public void importarDadosBanco() {
        System.out.println("Importando dados da conta " + numeroConta + " do banco " + banco);
    }

    public void sincronizarSaldo() {
        System.out.println("Sincronizando saldo da conta " + numeroConta + banco);
    }

    public void visualizarSaldo() {
        System.out.println("Exibindo saldo atual da conta " + numeroConta + ": R$ " + saldo);
    }

    public void importarExtrato(int dias) {
        System.out.println("Importando extrato dos últimos " + dias + " dias da conta " + numeroConta);
    }

    public void visualizarExtrato() {
        System.out.println("Exibindo extrato importado da conta " + numeroConta);
    }
}