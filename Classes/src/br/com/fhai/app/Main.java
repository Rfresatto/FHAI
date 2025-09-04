package br.com.fhai.app;

import br.com.fhai.model.usuarios.Usuarios;
import br.com.fhai.model.usuarios.Enderecos;
import br.com.fhai.model.usuarios.contasBancarias.ContasBancaria;
import br.com.fhai.model.usuarios.contasBancarias.cartoes.transacoes.Despesas;
import br.com.fhai.model.usuarios.contasBancarias.cartoes.transacoes.Receitas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Usuarios usuario = null;
        ContasBancaria conta = null;
        Despesas despesa;
        Receitas receita;
        int op;
        boolean login = false;
        boolean temConta = false;
        boolean temCartao = false;

        System.out.println("\n-~-~-~-~-~- Seja bem-vindo a FHAI !!! -~-~-~-~-~-");

        do {
            System.out.print(login ? """
                    Escolha uma opção:
                    \n1-Cadastrar-se
                    2-Exibir Dados do Perfil
                    3-Atualizar Perfil
                    4-Cadastrar Conta Bancária
                    5-Cadastrar um Cartão
                    6-Adicionar Despesas
                    7-Adicionar Receita
                    8-Exibir dados Bancarios
                    \n0-Sair
                    """ :
                    """
                            Escolha uma opção:
                            \n1-Cadastrar-se
                            2-login
                            \n0-Sair
                            """);
            op = sc.nextInt();

            switch (op) {
                case 1:
                    usuario = new Usuarios();

                    System.out.print("Digite o nome: ");
                    usuario.setNome(sc.nextLine());
                    System.out.print("Digite sua senha: ");
                    usuario.setSenha(sc.nextLine());
                    System.out.print("Digite seu email: ");
                    usuario.setEmail(sc.nextLine());
                    System.out.print("Digite um número de contato: ");
                    usuario.setContato(sc.nextInt());

                    login = true;
                    System.out.println("Usuário cadastrado e login efetuado! Olá, " + usuario.getNome());
                    break;

                case 2:
                    if (login) {
                        System.out.println(usuario.getDetalhes());
                    } else {
                        System.out.println("Nenhum usuário encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("Informe o nome da Rua, " +
                            "número, complemento, " +
                            "o CEP, " +
                            "cidade e o estado, ao final de cada um tecle: ENTER");
                    usuario.setEndereco(new Enderecos(sc.nextLine(),
                            sc.nextLine(),
                            sc.nextLine(),
                            sc.nextLine(),
                            sc.nextLine(),
                            sc.nextLine()));

                    System.out.println("Perfil atualizado com sucesso! ");
                    break;
                case 4:

                    temConta = true;
                    break;
                case 5:
                    if (!temConta) {
                        System.out.println("Você ainda não possui um conta bancária vinculada.\nTecle 4");
                    } else {
                        System.out.print("Digite o nome da sua conta Bancaria");
                        //usuario.setConta(new ContasBancaria(sc.nextLine()));
                        temCartao = true;
                    }
                    break;
                case 6:
                    if (!temCartao) {
                        System.out.println("Você ainda não possui um cartão vinculado a sua conta.\nTecle 4");
                    } else {

                        System.out.print("Digite o nome da sua Despesa");
                    }
                    break;
                case 7:
                    if (!temCartao) {
                        System.out.println("Você ainda não possui um cartão vinculado a sua conta.\nTecle 4");
                    } else {

                        System.out.print("Digite o nome da sua Receita: ");
                    }
                    break;
                case 8:
                    if (!temCartao){
                        System.out.println("Você precisa de uma conta e um cartão vinculado ao seu usuário para prosseguir.");
                    }else {
                        System.out.println(usuario.getDadosBancarios());

                    }
                case 0:
                    System.out.println("Até breve!");
                    break;
                default:
                    System.out.println("Opção inválida, Sistema Finalizado!");
            }
        } while (op != 0);

        sc.close();
    }
}
