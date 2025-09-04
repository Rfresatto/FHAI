package br.com.fhai.model.usuarios;

public class Enderecos {
    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;

    //construtores
    public Enderecos(){

    }

    public Enderecos(String logradouro, String numero, String complemento, String cep, String cidade, String estado) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    //getters e setters
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    //metodo
    public String getEnderecoCompleto(){
        return this.logradouro + ", " +
                this.numero + ", " +
                this.complemento + ", " +
                this.cep + ", " +
                this.cidade + ", " +
                this.estado;
    }
}


