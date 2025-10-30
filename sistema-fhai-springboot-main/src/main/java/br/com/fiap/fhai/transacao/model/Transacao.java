package br.com.fiap.fhai.transacao.model;

import br.com.fiap.fhai.cartao.model.Cartao;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "T_FHAI_TRANSACOES")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANSACOES")
    @SequenceGenerator(name = "SEQ_TRANSACOES", sequenceName = "SEQ_TRANSACOES", allocationSize = 1)
    private int id_transacao;
    private String nm_transacao;
    private String ds_transacao;
    private float vl_transacao;
    private String tp_transacao;
    private Date dt_transacao;

    @ManyToOne
    @JoinColumn(name = "ID_CARTAO")
    private Cartao cartao;

    public Transacao() {
    }

    public Transacao(int id_transacao, String nm_transacao, String ds_transacao, float vl_transacao, String tp_transacao, Cartao cartao, Date dt_transacao) {
        this.id_transacao = id_transacao;
        this.nm_transacao = nm_transacao;
        this.ds_transacao = ds_transacao;
        this.vl_transacao = vl_transacao;
        this.tp_transacao = tp_transacao;
        this.cartao = cartao;
        this.dt_transacao = dt_transacao;
    }

    public int getId_transacao() {
        return id_transacao;
    }

    public void setId_transacao(int id_transacao) {
        this.id_transacao = id_transacao;
    }

    public String getNm_transacao() {
        return nm_transacao;
    }

    public void setNm_transacao(String nm_transacao) {
        this.nm_transacao = nm_transacao;
    }

    public String getDs_transacao() {
        return ds_transacao;
    }

    public void setDs_transacao(String ds_transacao) {
        this.ds_transacao = ds_transacao;
    }

    public float getVl_transacao() {
        return vl_transacao;
    }

    public void setVl_transacao(float vl_transacao) {
        this.vl_transacao = vl_transacao;
    }

    public String getTp_transacao() {
        return tp_transacao;
    }

    public void setTp_transacao(String tp_transacao) {
        this.tp_transacao = tp_transacao;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Date getDt_transacao() {
        return dt_transacao;
    }

    public void setDt_transacao(Date dt_transacao) {
        this.dt_transacao = dt_transacao;
    }
}
