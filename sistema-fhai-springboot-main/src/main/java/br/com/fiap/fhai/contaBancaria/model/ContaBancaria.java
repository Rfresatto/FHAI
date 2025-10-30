package br.com.fiap.fhai.contaBancaria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "T_FHAI_CONTA")
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTA")
    @SequenceGenerator(name = "SEQ_CONTA", sequenceName = "SEQ_CONTA", allocationSize = 1)
    @Column(name = "ID_CONTA")
    private int id;

    @Column(name = "NR_CONTA")
    private String numeroConta;

    @Column(name = "NM_BANCO")
    private String nomeBanco;

    @Column(name = "TP_CONTA")
    private String tipoConta;

    @Column(name = "AGENCIA")
    private String agencia;

    @Column(name = "VL_SALDO")
    private double saldo;

    public ContaBancaria() {}

    public ContaBancaria(String numeroConta, String nomeBanco, String tipoConta, String agencia, double saldo) {
        this.numeroConta = numeroConta;
        this.nomeBanco = nomeBanco;
        this.tipoConta = tipoConta;
        this.agencia = agencia;
        this.saldo = saldo;
    }

    public ContaBancaria(int id, String numeroConta, String nomeBanco, String tipoConta, String agencia, double saldo) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.nomeBanco = nomeBanco;
        this.tipoConta = tipoConta;
        this.agencia = agencia;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}