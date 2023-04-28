package br.com.sinqia.cargos;

import java.math.BigDecimal;

public class DepartamentoPessoal extends Funcionario {


    public DepartamentoPessoal(String nome, BigDecimal salario) {
        super(nome, salario, Cargo.DEPARTAMENTO_PESSOAL);
    }
}
