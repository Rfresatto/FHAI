package br.com.fhai.model.usuario;

import br.com.fhai.model.usuario.contaBancaria.ContaBancaria;

public class Usuario extends Endereco {
    private String nome;
    private String senha;
    private String email;
    private long contato;
    private ContaBancaria conta;

    //  Contrutores
    public Usuario() {
        super();
    }

    @Override
    public String getEnderecoCompleto() {
        return this.getLogradouro() + ", n°: " +
                this.getNumero() + ", Comp: " +
                this.getComplemento() + ", CEP: " +
                this.getCep() + ", " +
                this.getCidade() + " - " +
                this.getEstado();
    }

    public Usuario(String logradouro, String numero, String complemento, int cep, String cidade, String estado, String nome, String senha, String email, long contato, ContaBancaria conta) {
        super(logradouro, numero, complemento, cep, cidade, estado);
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.contato = contato;
        this.conta = conta;
    }

    //getters e setters
    public String getNome() {
        return nome;
    }

    public Usuario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Usuario setEmail(String email) {
        this.email = email;
        return this;
    }

    public long getContato() {
        return contato;
    }

    public Usuario setContato(long contato) {
        this.contato = contato;
        return this;
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta(ContaBancaria conta) {
        this.conta = conta;
    }

    //  Métodos
    public void login(String name) {
        System.out.println("Login realizado! Bem vindo," + name + " !");
    }

    public String getDetalhes() {
        return "-~-~-~-~-~- Dados do Perfil -~-~-~-~-~-\n" +
                "\nNome: " + this.getNome() +
                "\nEmail: " + this.getEmail() +
                "\nContato: " + this.getContato();
    }

    public String getDetalhesCompleto() {
        return getDetalhes() +
                "\nEndereço: " + this.getEnderecoCompleto();
    }
}