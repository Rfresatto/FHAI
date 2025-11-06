package br.com.fiap.fhai.autenticacao.model;

public class UsuarioResponse {
    private int id;
    private String nome;
    private String email;
    private String sexo;
    private long contato;

    public UsuarioResponse() {}

    public UsuarioResponse(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}