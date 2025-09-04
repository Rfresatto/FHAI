package br.com.fhai.model.usuarios;

import br.com.fhai.model.usuarios.contasBancaria.ContasBancaria;

public class Usuarios extends Enderecos{
    private String nome;
    private String senha;
    private String email;
    private long contato;
    private Enderecos endereco;
    private ContasBancaria conta;

    //  Contrutores
    public Usuarios(){
    }

    public Usuarios(String nome, String senha, String email, long contato, Enderecos endereco, ContasBancaria conta) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.contato = contato;
        this.endereco = endereco;
        this.conta = conta;
    }

    //getters e setters
    public String getNome() {
        return nome;
    }

    public Usuarios setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public Usuarios setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Usuarios setEmail(String email) {
        this.email = email;
        return this;
    }

    public long getContato() {
        return contato;
    }

    public Usuarios setContato(long contato) {
        this.contato = contato;
        return this;
    }

    public Enderecos getEndereco() {
        return endereco;
    }

    public Usuarios setEndereco(Enderecos endereco) {
        this.endereco = endereco;
        return this;
    }

    public ContasBancaria getConta() {
        return conta;
    }

    public void setConta(ContasBancaria conta) {
        this.conta = conta;
    }

    //  Métodos
    public void login(String name){
        System.out.println("Login realizado! Bem vindo," + name + " !");
    }

    public String getDetalhes(){
        return "-~-~-~-~-~- Dados do Perfil -~-~-~-~-~-\n" +
                "\nNome: " + this.getNome() +
                "\nEmail: " + this.getEmail() +
                "\nContato: " + this.getContato();

    }

    public String getDetalhesCompleto(){
        return "-~-~-~-~-~- Dados do Perfil Atualizado -~-~-~-~-~-\n" +
                "\nNome: " + this.getNome() +
                "\nEmail: " + this.getEmail() +
                "\nContato: " + this.getContato() +
                "\nEndereço: " + this.getEndereco().getEnderecoCompleto();
    }


    public String getDadosBancarios(){
        return "-~-~-~-~-~- Dados Bancários -~-~-~-~-~-\n" +
                this.getConta().exibirDados();

    }

    public String exibirDadosCartao() {
        return "-~-~-~-~-~- Dados do Cartão -~-~-~-~-~-" +
                this.conta.getCartao().exibirDados();
    }
}