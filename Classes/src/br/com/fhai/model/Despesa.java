package br.com.fhai.model;

import java.sql.Date;

public class Despesa {
    private int idDespesa;
    private String nome;
    private String descricao;
    private double despesa;
    private Date data;
    private int idCategoria;
    private int idUsuario;

    //  Contrutores
    public Despesa() {

    }

    public Despesa(int idDespesa, String nome, String descricao, double despesa, Date data, int idCategoria, int idUsuario) {
        this.idDespesa = idDespesa;
        this.nome = nome;
        this.descricao = descricao;
        this.despesa = despesa;
        this.data = data;
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
    }

    //  Getters and Setters ... Atalho para criação padrão(IDE: Intellij): Alt + Ins
    public int getIdDespesa() {
        return idDespesa;
    }

    public Despesa setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Despesa setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Despesa setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public double getDespesa() {
        return despesa;
    }

    public Despesa setDespesa(double despesa) {
        this.despesa = despesa;
        return this;
    }

    public Date getData() {
        return data;
    }

    public Despesa setData(Date data) {
        this.data = data;
        return this;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public Despesa setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
        return this;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public Despesa setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    //  Métodos
    public void registrarDespesa() {
        System.out.println("Despesa registrada com sucesso!");
    }

    public void exibirDespesa() {
        System.out.println("---Dados da despesa---\nNome: " + nome + "\nDescrição: " + descricao + "\nValor: R$ " + despesa + "\nData: " + data);
    }

    public void excluirDespesa() {
        System.out.println("Despesa excluída com sucesso!");
    }

    public void atualizarDespesa() {
        System.out.println("Despesa editada com sucesso!");
    }
}

