public class CadastroUsuario {
    private String nome;
    private String nomeUsuario;
    private String senha;
    private String email;
    private int contato;
    private String endereco;

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