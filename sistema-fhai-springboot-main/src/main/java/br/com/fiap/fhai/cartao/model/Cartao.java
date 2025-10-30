package br.com.fiap.fhai.cartao.model;

import br.com.fiap.fhai.contaBancaria.model.ContaBancaria;
import br.com.fiap.fhai.transacao.model.Transacao;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "T_FHAI_CARTAO")
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CARTAO")
    @SequenceGenerator(name = "SEQ_CARTAO", sequenceName = "SEQ_CARTAO", allocationSize = 1)
    @Column(name = "ID_CARTAO")
    private int id_cartao;

    @Column(name = "NM_CARTAO", nullable = false, length = 20)
    private String nm_cartao;

    @Column(name = "NR_CARTAO", nullable = false, length = 16)
    private String nr_cartao;

    @Column(name = "TP_CARTAO", nullable = false, length = 10)
    private String tp_cartao; // crédito, débito

    @Column(name = "NM_TITULAR", nullable = false, length = 80)
    private String nm_titular;

    @Column(name = "DT_VALIDADE", nullable = false)
    private LocalDate dt_validade;

    @Column(name = "VL_SALDO", nullable = false)
    private double vl_saldo;

    @ManyToOne
    @JoinColumn(name = "ID_CONTA")
    private ContaBancaria contaBancaria;

    @OneToMany(mappedBy = "cartao")
    private List<Transacao> transacoes;

    public Cartao() {
    }

    public Cartao(int id_cartao, String nm_cartao, String nr_cartao, String tp_cartao, String nm_titular, LocalDate dt_validade, double vl_saldo, ContaBancaria contaBancaria) {
        this.id_cartao = id_cartao;
        this.nm_cartao = nm_cartao;
        this.nr_cartao = nr_cartao;
        this.tp_cartao = tp_cartao;
        this.nm_titular = nm_titular;
        this.dt_validade = dt_validade;
        this.vl_saldo = vl_saldo;
        this.contaBancaria = contaBancaria;
    }

    public int getId_cartao() {
        return id_cartao;
    }

    public void setId_cartao(int id_cartao) {
        this.id_cartao = id_cartao;
    }

    public String getNm_cartao() {
        return nm_cartao;
    }

    public void setNm_cartao(String nm_cartao) {
        this.nm_cartao = nm_cartao;
    }

    public String getNr_cartao() {
        return nr_cartao;
    }

    public void setNr_cartao(String nr_cartao) {
        this.nr_cartao = nr_cartao;
    }

    public String getTp_cartao() {
        return tp_cartao;
    }

    public void setTp_cartao(String tp_cartao) {
        this.tp_cartao = tp_cartao;
    }

    public String getNm_titular() {
        return nm_titular;
    }

    public void setNm_titular(String nm_titular) {
        this.nm_titular = nm_titular;
    }

    public LocalDate getDt_validade() {
        return dt_validade;
    }

    public void setDt_validade(LocalDate dt_validade) {
        this.dt_validade = dt_validade;
    }

    public double getVl_saldo() {
        return vl_saldo;
    }

    public void setVl_saldo(double vl_saldo) {
        this.vl_saldo = vl_saldo;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
}
