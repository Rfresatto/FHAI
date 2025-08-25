import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op;

    do {
        System.out.println("Escolha uma opção: \n1- Cadastrar usuário \n2- Exibir usuário \n3- Cadastrar Conta \n4- Exibir Conta \n5- Adicionar Receita \n6- Adicionar Despesa \n0- Sair");
        op = sc.nextInt();

        switch (op) {
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 0:
                System.out.println("Sistema Finalizado!");
        }

    } while (op != 0);

    }
}