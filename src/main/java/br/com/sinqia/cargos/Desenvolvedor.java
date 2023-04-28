package br.com.sinqia.cargos;

import java.math.BigDecimal;

public class Desenvolvedor extends Funcionario {

    public Desenvolvedor(String nome, BigDecimal salario) {
        super(nome, salario, Cargo.DESENVOLVEDOR);
    }
}
