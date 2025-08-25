import java.sql.Date;

public class Receita {
    private int idReceita;
    private String nome;
    private String descricao;
    private double valor;
    private Date data;
    private int idCategoria;
    private int idUsuario;

    public Receita (){

    }

    public Receita(int idReceita, String nome, String descricao, double valor, Date data, int idCategoria, int idUsuario) {
        this.idReceita = idReceita;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
    }

    public void registrarReceita() {
        System.out.println("Receita registrada com sucesso!");
    }

    public void exibirDetalhes() {
        System.out.println("---Dados da despesa---\nNome: " + nome + "\nDescrição: " + descricao + "\nValor: R$" + valor + "\nData: " + data);

    }

    public void atualizarReceita() {
        System.out.println("Registro de receita atualizado com sucesso!");
    }

    public void excluirReceita() {
        System.out.println("Receita excluída com sucesso!");
    }
}
