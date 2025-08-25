import java.sql.Date;

public class Despesa {
    private int idDespesa;
    private String nome;
    private String descricao;
    private double despesa;
    private Date data;
    private int idCategoria;
    private int idUsuario;

    public Despesa(){

    }

    public Despesa(int idDespesa, String nome, String descricao, double despesa, Date data, int idCategoria, int idUsuario){
    this.idDespesa = idDespesa;
    this.nome = nome;
    this.descricao = descricao;
    this.despesa = despesa;
    this.data = data;
    this.idCategoria = idCategoria;
    this.idUsuario = idUsuario;
    }

    public void registrarDespesa() {
    System.out.println("Despesa registrada com sucesso!");
    }

    public void exibirDespesa() {
        System.out.println("---Dados da despesa---\nNome: " + nome + "\nDescrição: " + descricao + "\nValor: R$ " + despesa + "\nData: " + data);
    }

    public void excluirDespesa() {
        System.out.println("Despesa excluída com sucesso!");
    }

    public void atualizarDespesa() {
        System.out.println("Despesa editada com sucesso!");
    }
}

