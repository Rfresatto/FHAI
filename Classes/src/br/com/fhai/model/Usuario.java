package br.com.fhai.model;

public class Usuario {
    private String usuario;
    private String senha;
    Conta conta;

//  Contrutores
    public Usuario (){

    }

    public Usuario(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
    }

    // Getters and Setters ... Atalho para criação padrão(IDE: Intellij): Alt + Ins
    public String getUsuario() {
        return usuario;
    }

    public Usuario setUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public Conta getConta() {
        return conta;
    }

    public Usuario setConta(Conta conta) {
        this.conta = conta;
        return this;
    }

//  Métodos
    public void inserirUsuario(){
        System.out.println("Inserindo Usuário...");
    }

    public void inserirSenha(){
        System.out.println("Inserindo senha...");
    }

    public void acessarAplicacao(){
        System.out.println("Acessando aplicação...");
    }

    public void recuperarSenha(){
        System.out.println("Acessando recuperação de senha...");
    }
}