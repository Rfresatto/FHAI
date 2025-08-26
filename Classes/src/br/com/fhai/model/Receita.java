package br.com.fhai.model;

import java.sql.Date;
public class Receita {
    private int idReceita;
    private String nome;
    private String descricao;
    private double valor;
    private Date data;
    private int idCategoria;
    private int idUsuario;

//  Contrutores
    public Receita (){

    }

    public Receita(int idReceita, String nome, String descricao, double valor, Date data, int idCategoria, int idUsuario) {
        this.idReceita = idReceita;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
    }

//  Getters and Setters ... Atalho para criação padrão(IDE: Intellij): Alt + Ins
    public int getIdReceita() {
        return idReceita;
    }

    public Receita setIdReceita(int idReceita) {
        this.idReceita = idReceita;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Receita setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Receita setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public double getValor() {
        return valor;
    }

    public Receita setValor(double valor) {
        this.valor = valor;
        return this;
    }

    public Date getData() {
        return data;
    }

    public Receita setData(Date data) {
        this.data = data;
        return this;
    }

    public int getIdCategoria() {
        return idCategoria;

    }

    public Receita setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
        return this;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public Receita setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    //  Métodos
    public void registrarReceita() {
        System.out.println("Receita registrada com sucesso!");
    }

    public void exibirDetalhes() {
        System.out.println("---Dados da despesa---\nNome: " + nome + "\nDescrição: " + descricao + "\nValor: R$" + valor + "\nData: " + data);
    }

    public void atualizarReceita() {
        System.out.println("Registro de receita atualizado com sucesso!");
    }

    public void excluirReceita() {
        System.out.println("Receita excluída com sucesso!");
    }
}
