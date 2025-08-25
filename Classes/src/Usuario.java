public class Usuario {
    private String usuario;
    private String senha;

    public Usuario (){

    }

    public Usuario(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
    }

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
        System.out.println("Acessando recupareção de senha...");
    }
}