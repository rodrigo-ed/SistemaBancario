 # Sistema Bancário
 
Este projeto consiste em um sistema bancário simples de console, implementado em **Java** e estruturado com princípios de **Orientação a Objetos**. Ele permite aos usuários criar uma conta e realizar operações bancárias básicas.
 
 ## Funcionalidades
 
*   **Criação de Conta:** Coleta o nome e o CPF do usuário para criar uma instância de `ContaBancaria`.
*   **Consultar Saldo:** Exibe o saldo atual da conta, formatado em moeda local (R$).
*   **Depósito:** Permite ao usuário adicionar um valor à sua conta. O sistema valida se o valor é positivo.
*   **Saque:** Permite ao usuário retirar um valor da conta. O sistema valida se o valor é positivo e se há saldo suficiente.
 
 ## Tecnologias Utilizadas
*   **Java**: Linguagem de programação principal.
*   **Maven**: Ferramenta de automação de compilação e gerenciamento de dependências.
*   **`java.util.Scanner`**: Para leitura de dados de entrada do usuário via console.
*   **`java.math.BigDecimal`**: Para garantir a precisão nos cálculos monetários, evitando problemas de arredondamento com tipos `double` ou `float`.
*   **`java.text.NumberFormat`**: Para a correta formatação de valores em moeda local (R$).

## Como Executar o Projeto

### Requisitos
*   Java Development Kit (JDK) 11 ou superior.
*   Apache Maven.

### Passos para execução via Maven (Recomendado)

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/seu-usuario/SistemaBancario.git
    cd SistemaBancario
    ```

2.  **Execute o projeto com o Maven:**
    O Maven irá compilar e executar a classe principal definida no `pom.xml`.
    ```bash
    mvn compile exec:java
    ```

### Passos para execução manual

1.  Navegue até o diretório que contém os fontes: `cd src/main/java`.
2.  Compile as classes Java:
    ```bash
    javac br/com/sistema/bancario/ContaBancaria.java br/com/sistema/bancario/SistemaBancario.java
    ```
3.  Execute a classe principal:
    ```bash
    java br.com.sistema.bancario.SistemaBancario
    ```

 ## Estrutura do Código
 
O projeto foi refatorado para seguir os princípios da **Orientação a Objetos**, separando as responsabilidades em duas classes principais, o que melhora a organização e a manutenibilidade do código:

*   `ContaBancaria.java`: Classe de modelo (Model) que encapsula os dados e as **regras de negócio** da conta.
    *   **Responsabilidades:** Gerenciar o saldo, validar as operações de depósito (valores positivos) e saque (valores positivos e saldo suficiente).
    *   Utiliza `BigDecimal` para os atributos de valor, garantindo a precisão monetária.

*   `SistemaBancario.java`: Classe principal que lida com a **interação com o usuário** (View/Controller).
    *   **Responsabilidades:** Exibir o menu, capturar as entradas do usuário, formatar os dados para exibição (moeda, CPF) e orquestrar as chamadas para os métodos da classe `ContaBancaria`.
    *   Trata as exceções (`IllegalArgumentException`, `NumberFormatException`) para fornecer feedback claro ao usuário em caso de erros.


