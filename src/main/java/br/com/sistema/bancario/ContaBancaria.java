package br.com.sistema.bancario;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Representa uma conta bancária, encapsulando o saldo e as regras de negócio.
 * Usa BigDecimal para garantir a precisão monetária.
 */
public class ContaBancaria {
    private final String nomeTitular;
    private final String cpf;
    private BigDecimal saldo; // Usamos BigDecimal para precisão

    // Construtor
    public ContaBancaria(String nomeTitular, String cpf) {
        this.nomeTitular = nomeTitular;
        this.cpf = cpf;
        // Inicializa o saldo com zero
        this.saldo = BigDecimal.ZERO; 
    }

    /**
     * Realiza um depósito na conta.
     * @param valor O valor a ser depositado.
     * @throws IllegalArgumentException Se o valor for menor ou igual a zero.
     */
    public void depositar(BigDecimal valor) {
        // Validação da regra de negócio
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            // Lança uma exceção, deixando o tratamento para a camada de I/O (SistemaBancario)
            throw new IllegalArgumentException("O valor do deposito deve ser maior que zero.");
        }
        this.saldo = this.saldo.add(valor);
    }

    /**
     * Realiza um saque na conta.
     * @param valor O valor a ser sacado.
     * @return true se o saque for bem-sucedido, false caso contrário (saldo insuficiente).
     * @throws IllegalArgumentException Se o valor for menor ou igual a zero.
     */
    public boolean sacar(BigDecimal valor) {
        // Validação da regra de negócio
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
        }
        
        // Verifica se há saldo suficiente
        if (valor.compareTo(this.saldo) > 0) {
            return false; // Falha na regra de negócio (saldo insuficiente)
        }
        
        this.saldo = this.saldo.subtract(valor);
        return true; // Sucesso
    }

    // --- Getters ---
    
    public String getNomeTitular() {
        return nomeTitular;
    }

    public String getCpf() {
        return cpf;
    }

    // Retorna o saldo formatado com duas casas decimais
    public BigDecimal getSaldo() {
        return saldo.setScale(2, RoundingMode.HALF_UP); 
    }
}