package br.com.sinqia.cargos;

import br.com.sinqia.cargos.Cargo;

import java.math.BigDecimal;

abstract public class Funcionario {

    private String nome;
    private BigDecimal salario;
    private Cargo cargo;

    public Funcionario(String nome, BigDecimal salario, Cargo cargo) {
        this.nome = nome;
        this.salario = salario;
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getNome() {
        return nome;
    }
}
