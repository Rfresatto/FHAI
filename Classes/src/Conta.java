public class Conta {
    private String banco;
    private String numeroConta;
    private String agencia;
    private String tipoConta; // "corrente", "poupanca", "salario"
    private double saldo;
    private String cpfTitular;
    private Cartao cartao;

    public Conta() {
    }

    public Conta(String banco, String numeroConta, String agencia, String tipoConta, String cpfTitular) {
        this.banco = banco;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.tipoConta = tipoConta;
        this.cpfTitular = cpfTitular;
    }

    public void importarDadosBanco() {
        System.out.println("Importando dados da conta " + numeroConta + " do banco " + banco);
    }

    public void sincronizarSaldo() {
        System.out.println("Sincronizando saldo da conta " + numeroConta + banco);
    }

    public void visualizarSaldo() {
        System.out.println("Exibindo saldo atual da conta " + numeroConta + ": R$ " + saldo);
    }

    public void importarExtrato(int dias) {
        System.out.println("Importando extrato dos últimos " + dias + " dias da conta " + numeroConta);
    }

    public void visualizarExtrato() {
        System.out.println("Exibindo extrato importado da conta " + numeroConta);
    }
}