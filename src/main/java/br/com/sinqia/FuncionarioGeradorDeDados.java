package br.com.sinqia;

import br.com.sinqia.GeradorDeDados;
import br.com.sinqia.cargos.*;
import br.com.sinqia.repositories.Repository;

import java.math.BigDecimal;

public class FuncionarioGeradorDeDados implements GeradorDeDados {
    private final Repository repository;

    public FuncionarioGeradorDeDados(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void gerarDados() {
        repository.save(new Desenvolvedor("thiago", new BigDecimal("5000")));
        repository.save(new AnalistaDeSistema("diego", new BigDecimal("3500")));
        repository.save(new DepartamentoPessoal("kleberton", new BigDecimal("2700")));
    }

}
