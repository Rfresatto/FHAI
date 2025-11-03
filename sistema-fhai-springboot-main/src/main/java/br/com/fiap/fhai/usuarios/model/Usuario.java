package br.com.fiap.fhai.usuarios.model;

import br.com.fiap.fhai.endereco.model.Endereco;
import br.com.fiap.fhai.transacao.model.Transacao;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_FHAI_USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIOS")
    @SequenceGenerator(name = "SEQ_USUARIOS", sequenceName = "SEQ_USUARIOS", allocationSize = 1)
    @Column(name = "ID_USUARIO")
    private int id;

    @Column(name = "NM_USUARIO")
    private String nome;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NR_CONTATO")
    private long contato;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Transacao> transacoes = new ArrayList<>();


    public Usuario() {}

    public Usuario(String nome, String senha, String sexo, String email, long contato) {
        this.nome = nome;
        this.senha = senha;
        this.sexo = sexo;
        this.email = email;
        this.contato = contato;
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getContato() {
        return contato;
    }

    public void setContato(long contato) {
        this.contato = contato;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
}