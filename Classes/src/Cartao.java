import java.sql.Date;

public class Cartao {
    private String numeroCartao;
    private String bandeira;
    private String tipoCartao; // crédito, débito
    private String nomeTitular;
    private Date dataValidade;
    private String cvv;
    private double limite;
    private double faturaAtual;
    private Despesa saida;
    private Receita entrada;
    Conta conta;

    public Cartao() {
    }

    public Cartao(String tipoCartao, String bandeira, String numeroCartao, String nomeTitular, Conta conta, Date dataValidade, String cvv) {
        this.tipoCartao = tipoCartao;
        this.bandeira = bandeira;
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.conta = conta;
        this.dataValidade = dataValidade;
        this.cvv = cvv;
    }

    public void sincronizarFatura() {
        System.out.println("Sincronizando fatura atual do cartão :" + numeroCartao);
    }

    public void importarTransacoesCartao(int dias) {
        System.out.println("Importando transações do cartão :" + numeroCartao);
    }

    public void visualizarLimite() {
        System.out.println("Limite do cartão " + numeroCartao + ": R$ " + limite + " | Disponível R$ " + (limite - faturaAtual));
    }

    public void visualizarFatura() {
        System.out.println("Exibindo fatura atual do cartão " + numeroCartao + ": R$ " + faturaAtual);
    }

    public void importarExtratoCartao() {
        System.out.println("Importando extrato detalhado do cartão " + numeroCartao);
    }

    public void categorizarGastosCartao() {
        System.out.println("Categorizando gastos do cartão " + numeroCartao + " automaticamente");
    }

    public void analisarPadraoGastos() {
        System.out.println("Analisando padrão de gastos do cartão " + numeroCartao);
    }
}