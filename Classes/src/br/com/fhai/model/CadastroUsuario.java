package br.com.fhai.model;

public class CadastroUsuario {
    private String nome;
    private String nomeUsuario;
    private String senha;
    private String email;
    private int contato;
    private String endereco;
    Usuario usuario;


//  Construtores
    public CadastroUsuario(){

    }

    public CadastroUsuario(String nome, String nomeUsuario, String senha, String email, int contato, String endereco){
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.email = email;
        this.contato = contato;
        this.endereco = endereco;
    }

//  Getters and Setters ... Atalho para criação padrão(IDE: Intellij): Alt + Ins
    public String getNome() {
        return nome;
    }

    public CadastroUsuario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public CadastroUsuario setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public CadastroUsuario setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CadastroUsuario setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getContato() {
        return contato;
    }

    public CadastroUsuario setContato(int contato) {
        this.contato = contato;
        return this;
    }

    public String getEndereco() {
        return endereco;
    }

    public CadastroUsuario setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

//  Métodos
    public void inserirNome(){
        System.out.println("Cadastrando nome...");
    }

    public void inserirNomeUsuario(){
        System.out.println("Cadastrando nome de usuário...");
    }

    public void inserirSenha(){
        System.out.println("Cadastrando senha...");
    }

    public void inserirEmail(){
        System.out.println("Cadastrando E-mail...");
    }

    public void inserirContato(){
        System.out.println("Cadastrando contato...");
    }

    public void inserirEndereço(){
        System.out.println("Cadastrando endereço...");
    }

}