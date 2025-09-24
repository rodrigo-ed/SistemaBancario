# Sistema Bancário

Este projeto consiste em um sistema bancário simples de console, implementado em **Java**. Ele permite aos usuários criar uma conta simplificada e realizar operações bancárias básicas como consulta de saldo, depósito e saque.

## Funcionalidades

*   **Criação de Conta:** Coleta o nome, sobrenome e CPF do usuário para iniciar a sessão.
*   **Consultar Saldo:** Exibe o saldo atual da conta, formatado em moeda local (R$).
*   **Depósito:** Permite ao usuário adicionar um valor à sua conta. O sistema valida se o valor é positivo.
*   **Saque:** Permite ao usuário retirar um valor da conta. O sistema valida se o valor é positivo e se há saldo suficiente.
*   **Formatação de CPF:** Exibe o CPF do titular no formato `XXX.XXX.XXX-XX`.

## Tecnologias Utilizadas
*   **Java**: Linguagem de programação principal.
*   **`java.util.Scanner`**: Para leitura de dados de entrada do usuário via console.
*   **`java.text.DecimalFormat`**: Para formatação dos valores monetários.

## Estrutura do Código

Toda a lógica da aplicação está contida no arquivo `SistemaBancario.java`.

*   `main(String[] args)`: Método principal que inicia a aplicação, coleta os dados do usuário e controla o loop de operações.
*   `exibirMenu()`: Método estático responsável por imprimir o menu de opções no console.
*   `formatarCPF(String cpf)`: Método estático utilitário para formatar a string do CPF para uma melhor exibição.
