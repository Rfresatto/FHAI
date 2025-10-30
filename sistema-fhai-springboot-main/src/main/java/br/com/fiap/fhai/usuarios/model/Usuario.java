package br.com.fiap.fhai.usuarios.model;

import br.com.fiap.fhai.contaBancaria.model.ContaBancaria;
import br.com.fiap.fhai.endereco.model.Endereco;
import jakarta.persistence.*;

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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_ENDERECO")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_CONTA")
    private ContaBancaria contaBancaria;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }
}