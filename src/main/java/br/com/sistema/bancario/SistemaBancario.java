package br.com.sistema.bancario;

/**
 *
 * @author eduar
 */


import java.text.DecimalFormat; // Importa a classse Scanner para poder ler dados do teclado
import java.util.Scanner; // Importa a classe DecimalFormat para formatar números decimais

public class SistemaBancario {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) { // "try" é usado para garantir que o scanner seja fechado automaticamente
            DecimalFormat formatoMoeda = new DecimalFormat("R$ #,##0.00");

            // Coleta de dados do usuário
            System.out.println("SEJA BEM-VINDO");
            System.out.print("Informe seu primeiro nome: ");
            String nome = scanner.nextLine();

            System.out.print("Informe seu sobrenome: ");
            String sobrenome = scanner.nextLine();

            System.out.print("Informe seu CPF (apenas numeros): ");
            String cpf = scanner.nextLine();

            // Inicializa a conta bancária
            double saldo = 0.0;
            boolean continuarOperacao = true;
            int opcao;

            System.out.println("\nConta criada com sucesso!");
            System.out.println("Titular: " + nome + " " + sobrenome);
            System.out.println("CPF: " + formatarCPF(cpf));

            // Loop principal do programa
            while (continuarOperacao) {
                exibirMenu();

                try {
                    opcao = Integer.parseInt(scanner.nextLine());

                    switch (opcao) {
                        case 1 -> {
                            // Consultar saldo
                            System.out.println("\n=== CONSULTA DE SALDO ===");
                            System.out.println("Saldo atual: " + formatoMoeda.format(saldo));
                        }

                        case 2 -> {
                            // Realizar depósito
                            System.out.println("\n=== DEPÓSITO ===");
                            System.out.print("Informe o valor para deposito: R$ ");
                            try {
                                double valorDeposito = Double.parseDouble(scanner.nextLine().replace(",", "."));
                                if (valorDeposito <= 0) {
                                    System.out.println("Erro: O valor do deposito deve ser maior que zero.");
                                } else {
                                    saldo += valorDeposito;
                                    System.out.println("Depósito realizado com sucesso.");
                                    System.out.println("Novo saldo: " + formatoMoeda.format(saldo));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Erro: Valor inválido para deposito.");
                            }
                        }

                        case 3 -> {
                            // Realizar saque
                            System.out.println("\n=== SAQUE ===");
                            System.out.print("Informe o valor para saque: R$ ");
                            try {
                                double valorSaque = Double.parseDouble(scanner.nextLine().replace(",", "."));
                                if (valorSaque <= 0) {
                                    System.out.println("Erro: O valor do saque deve ser maior que zero.");
                                } else if (valorSaque > saldo) {
                                    System.out.println("Erro: Saldo insuficiente para realizar o saque.");
                                    System.out.println("Saldo atual: " + formatoMoeda.format(saldo));
                                } else {
                                    saldo -= valorSaque;
                                    System.out.println("Saque realizado com sucesso.");
                                    System.out.println("Novo saldo: " + formatoMoeda.format(saldo));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Erro: Valor invalido para saque.");
                            }
                        }

                        case 4 -> {
                            // Encerrar aplicação
                            System.out.println("\n=== ENCERRAMENTO ===");
                            System.out.println("Obrigado por utilizar nosso sistema bancario, " + nome + "!");
                            System.out.println("Saldo final: " + formatoMoeda.format(saldo));
                            continuarOperacao = false;
                        }

                        default ->
                            System.out.println("Opção invalida. Por favor, escolha uma opção válida.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Por favor, digite um numero para selecionar a opção.");
                }

                if (continuarOperacao) {
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                }
            }
        }
    }

    // Exibe o menu de opções
    private static void exibirMenu() {
        System.out.println("\n=== MENU DE OPERACOES ===");
        System.out.println("1 - Consultar Saldo");
        System.out.println("2 - Realizar Deposito");
        System.out.println("3 - Realizar Saque");
        System.out.println("4 - Encerrar");
        System.out.print("Escolha uma opcao: ");
    }

    // Formata o CPF para o padrão XXX.XXX.XXX-XX
    private static String formatarCPF(String cpf) {
        if (cpf.length() != 11) {
            return cpf; // Retorna sem formatação se não tiver 11 dígitos
        }
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "."
                + cpf.substring(6, 9) + "-" + cpf.substring(9);
    }
}