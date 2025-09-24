package br.com.sistema.bancario;

import java.math.BigDecimal;
import java.text.NumberFormat; // Melhor para formatação monetária
import java.util.Locale;
import java.util.Scanner;

public class SistemaBancario {

    // Formatador de moeda regional (Brasil)
    private static final NumberFormat FORMATO_MOEDA = 
            NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public static void main(String[] args) {
        // try-with-resources para garantir o fechamento do Scanner
        try (Scanner scanner = new Scanner(System.in)) { 
            
            System.out.println("SEJA BEM-VINDO");
            
            // Coleta e validação inicial de dados (pode ser aprimorada com métodos)
            System.out.print("Informe seu primeiro nome: ");
            String nome = scanner.nextLine();

            System.out.print("Informe seu CPF (apenas numeros): ");
            String cpf = scanner.nextLine();
            
            // Cria a instância da classe de negócio
            ContaBancaria conta = new ContaBancaria(nome, cpf);

            System.out.println("\nConta criada com sucesso!");
            System.out.println("Titular: " + conta.getNomeTitular());
            System.out.println("CPF: " + formatarCPF(conta.getCpf()));

            // Loop principal
            boolean continuarOperacao = true;
            while (continuarOperacao) {
                exibirMenu();
                
                try {
                    // Tenta ler a opção. nextLine() e Integer.parseInt() é robusto.
                    int opcao = Integer.parseInt(scanner.nextLine()); 

                    switch (opcao) {
                        case 1 -> consultarSaldo(conta);
                        case 2 -> realizarDeposito(scanner, conta);
                        case 3 -> realizarSaque(scanner, conta);
                        case 4 -> {
                            System.out.println("Obrigado por utilizar nosso sistema, " + conta.getNomeTitular() + "!");
                            System.out.println("Saldo final: " + FORMATO_MOEDA.format(conta.getSaldo()));
                            continuarOperacao = false;
                        }
                        default -> System.out.println("Opção invalida. Por favor, escolha uma opção válida.");
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
    
    // --- Métodos de Interação (Delegam a lógica para ContaBancaria) ---

    private static void consultarSaldo(ContaBancaria conta) {
        System.out.println("\n=== CONSULTA DE SALDO ===");
        // Uso do NumberFormat padrão
        System.out.println("Saldo atual: " + FORMATO_MOEDA.format(conta.getSaldo())); 
    }

    private static void realizarDeposito(Scanner scanner, ContaBancaria conta) {
        System.out.println("\n=== DEPÓSITO ===");
        BigDecimal valor = lerValorMonetario(scanner, "Informe o valor para deposito: ");
        
        try {
            conta.depositar(valor); // Delega a regra de negócio para a classe Conta
            System.out.println("Depósito realizado com sucesso.");
            System.out.println("Novo saldo: " + FORMATO_MOEDA.format(conta.getSaldo()));
        } catch (IllegalArgumentException e) {
            // Trata a exceção lançada pela classe de negócio (valor <= 0)
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void realizarSaque(Scanner scanner, ContaBancaria conta) {
        System.out.println("\n=== SAQUE ===");
        BigDecimal valor = lerValorMonetario(scanner, "Informe o valor para saque: ");
        
        try {
            if (conta.sacar(valor)) { // Delega a regra de negócio para a classe Conta
                System.out.println("Saque realizado com sucesso.");
                System.out.println("Novo saldo: " + FORMATO_MOEDA.format(conta.getSaldo()));
            } else {
                System.out.println("Erro: Saldo insuficiente para realizar o saque.");
                System.out.println("Saldo atual: " + FORMATO_MOEDA.format(conta.getSaldo()));
            }
        } catch (IllegalArgumentException e) {
            // Trata a exceção lançada pela classe de negócio (valor <= 0)
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    // --- Métodos Auxiliares ---
    
    // Método robusto para leitura de valores monetários (BigDecimal)
    private static BigDecimal lerValorMonetario(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + FORMATO_MOEDA.getCurrency().getSymbol() + " ");
            String entrada = scanner.nextLine().trim().replace(",", "."); // Aceita vírgula e ponto

            try {
                // Tenta criar um BigDecimal
                return new BigDecimal(entrada); 
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, digite um número (Ex: 1500.50).");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU DE OPERACOES ===");
        System.out.println("1 - Consultar Saldo");
        System.out.println("2 - Realizar Deposito");
        System.out.println("3 - Realizar Saque");
        System.out.println("4 - Encerrar");
        System.out.print("Escolha uma opcao: ");
    }
    
    // Formata o CPF (Método de utilidade)
    private static String formatarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return cpf; 
        }
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "."
               + cpf.substring(6, 9) + "-" + cpf.substring(9);
    }
}