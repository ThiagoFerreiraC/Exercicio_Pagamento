package br.com.sinqia.cargos;

import java.math.BigDecimal;

public class AnalistaDeSistema extends Funcionario {

    public AnalistaDeSistema(String nome, BigDecimal salario) {
        super(nome, salario, Cargo.ANALISTA_DE_SISTEMA);
    }
}
