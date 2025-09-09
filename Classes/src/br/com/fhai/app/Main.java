package br.com.fhai.app;

import br.com.fhai.model.usuarios.Usuarios;
import br.com.fhai.model.usuarios.contasBancaria.ContasBancaria;
import br.com.fhai.model.usuarios.contasBancaria.cartoes.Cartoes;
import br.com.fhai.model.usuarios.contasBancaria.cartoes.Transacoes;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static void menuLogado() {
        System.out.print("""
                
                \n1- Exibir dados do perfil
                2- Cadastro de endereço
                3- Cadastrar/Exibir conta bancária
                4- Cadastrar/Exibir cartão
                5- Adicionar/Exibir despesas
                6- Adicionar/Exibir receita
                7- Exibir transações
                \n0- Sair
                """);
        System.out.print("Escolha uma opção: ");
    }

    private static void menuNaoLogado() {
        System.out.print("""
                
                \n1- Cadastrar-se
                2- login
                \n0- Sair
                """);
        System.out.print("Escolha uma opção: ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Usuarios usuario = null;

        int op;
        boolean login = false;
        boolean temConta = false;
        boolean temCartao = false;
        boolean temTransacao = false;

        System.out.println("\n-~-~-~-~-~- Seja bem-vindo a FHAI !!! -~-~-~-~-~-");

        do {
            if (login) {
                menuLogado();
            } else {
                menuNaoLogado();
            }
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    if (!login) {
                        usuario = new Usuarios();

                        System.out.print("Digite o nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Digite sua senha: ");
                        String senha = sc.nextLine();
                        System.out.print("Digite seu email: ");
                        String email = sc.nextLine();
                        System.out.print("Digite um número de contato: ");
                        long contato = sc.nextLong();

                        usuario.setNome(nome).setSenha(senha).setEmail(email).setContato(contato);

                        login = true;
                        System.out.println("Usuário cadastrado e login efetuado! Olá, " + usuario.getNome());
                    } else {
                        System.out.println(usuario.getLogradouro() != null ? usuario.getDetalhesCompleto() : usuario.getDetalhes());
                    }
                    break;
                case 2:
                    if (login) {
                        System.out.println("Digite o nome da Rua:");
                        usuario.setLogradouro(sc.nextLine());
                        System.out.println("Digite o número: ");
                        usuario.setNumero(sc.nextLine());
                        System.out.println("Digite o complemento: ");
                        usuario.setComplemento(sc.nextLine());
                        System.out.println("Digite o CEP: ");
                        usuario.setCep(sc.nextInt());
                        sc.nextLine();
                        System.out.println("Digite a cidade: ");
                        usuario.setCidade(sc.nextLine());
                        System.out.println("Digite o estado: ");
                        usuario.setEstado(sc.nextLine());

                        System.out.println("Perfil atualizado com sucesso!");
                    } else {
                        System.out.print("Digite o nome: ");
                        String nome = sc.nextLine();

                        System.out.print("Digite sua senha: ");
                        String senha = sc.nextLine();

                        if (usuario != null && Objects.equals(nome, usuario.getNome()) && Objects.equals(senha, usuario.getSenha())) {
                            System.out.println("Bem vindo, " + usuario.getNome());
                            login = true;
                        } else {
                            System.out.println("Usuário ou senha inválidos");
                        }
                    }
                    break;
                case 3:
                    if (login) {
                        if (temConta) {
                            System.out.println(usuario.getDadosBancarios());
                        } else {
                            System.out.println("Digite o nome do Banco: ");
                            String nome = sc.nextLine();
                            System.out.println("Digite o número da conta: ");
                            String numeroConta = sc.nextLine();
                            System.out.println("Digite o número da agência: ");
                            String numeroAgencia = sc.nextLine();
                            System.out.println("Digite o tipo da conta (exemplo: corrente, poupança, salário): ");
                            String tipoConta = sc.nextLine();

                            usuario.setConta(new ContasBancaria(nome, numeroConta, numeroAgencia, tipoConta));
                            temConta = true;
                        }
                    } else {
                        System.out.println("Opção inválida");
                        temConta = true;
                    }
                    break;
                case 4:
                    if (login) {
                        if (!temConta) {
                            System.out.println("Você ainda não possui um conta bancária vinculada.\nTecle 3");
                        } else {
                            if (temCartao) {
                                System.out.println(usuario.exibirDadosCartao());
                            } else {
                                System.out.print("Digite o nome do cartão: ");
                                String nome = sc.nextLine();

                                System.out.print("Digite o número do cartão: ");
                                long numero = sc.nextLong();
                                sc.nextLine();

                                System.out.print("Digite o Tipo do cartão(débido/crédito): ");
                                String tipoCartao = sc.nextLine();

                                System.out.print("Digite o nome do Titular do cartão: ");
                                String nomeTitular = sc.nextLine();

                                System.out.print("Digite o Data de validade do cartão (MM/AA): ");
                                String dataValidade = sc.nextLine();

                                System.out.print("Digite o código de segurança do cartão: ");
                                int cvv = sc.nextInt();
                                sc.nextLine();

                                usuario.getConta().setCartao(new Cartoes(nome, numero, tipoCartao, nomeTitular, dataValidade, cvv));
                                temCartao = true;
                            }
                        }
                    } else {
                        System.out.println("Opção inválida");
                    }
                    break;
                case 5:
                    if (login) {
                        if (!temCartao) {
                            System.out.println("Você ainda não possui um cartão vinculado a sua conta.\nTecle 4");
                        } else {
                            System.out.print("Digite o nome da sua Despesa: ");
                            String nomeDespesa = sc.nextLine();

                            System.out.print("Digite uma descrição: ");
                            String descricao = sc.nextLine();

                            System.out.print("Digite o valor da despesa: ");
                            double valor = sc.nextDouble();
                            sc.nextLine();

                            System.out.print("Digite a data da despesa (DD/MM/AAAA): ");
                            String data = sc.nextLine();

                            Transacoes despesa = new Transacoes(nomeDespesa, descricao, valor, data);
                            usuario.getConta().getCartao().adicionarTransacao(despesa, false);

                            System.out.println("Despesa adicionada com sucesso!");
                            temTransacao = true;
                        }
                    } else {
                        System.out.println("Opção inválida");
                    }
                    break;
                case 6:
                    if (login) {
                        if (!temCartao) {
                            System.out.println("Você ainda não possui um cartão vinculado a sua conta.\nTecle 4");
                        } else {
                            System.out.print("Digite o nome da sua Receita: ");
                            String nomeReceita = sc.nextLine();

                            System.out.print("Digite uma descrição: ");
                            String descricao = sc.nextLine();

                            System.out.print("Digite o valor da receita: ");
                            double valor = sc.nextDouble();
                            sc.nextLine();

                            System.out.print("Digite a data da receita (DD/MM/AAAA): ");
                            String data = sc.nextLine();

                            Transacoes receita = new Transacoes(nomeReceita, descricao, valor, data);
                            usuario.getConta().getCartao().adicionarTransacao(receita, true);

                            System.out.println("Receita adicionada com sucesso!");
                            temTransacao = true;
                        }
                    } else {
                        System.out.println("Opção inválida");
                    }
                    break;
                case 7:
                    if (login) {
                        if (temTransacao) {
                            System.out.println("\nHistórico de Transações:");
                            for (Transacoes t : usuario.getConta().getCartao().getTransacoes()) {
                                System.out.println("- " + t.getNome() + " | " + t.getDescricao() + " | R$ " + t.getValor() + " | " + t.getData());
                            }
                            System.out.println("Saldo atual: R$ " + usuario.getConta().getCartao().getSaldo());
                        } else {
                            System.out.println("Nenhuma transação registrada.");
                        }
                    } else {
                        System.out.println("Opção inválida");
                    }
                    break;
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
