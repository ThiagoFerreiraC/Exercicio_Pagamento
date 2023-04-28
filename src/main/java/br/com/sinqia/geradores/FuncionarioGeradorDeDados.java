package br.com.sinqia.geradores;

import br.com.sinqia.cargos.*;
import br.com.sinqia.repositories.Repository;

import java.math.BigDecimal;

public class FuncionarioGeradorDeDados implements GeradorDeDados {
    private final Repository<Funcionario> repository;

    public FuncionarioGeradorDeDados(Repository<Funcionario> repository) {
        this.repository = repository;
    }

    @Override
    public void gerarDados() {
        repository.save(new Desenvolvedor("maria", new BigDecimal("5000")));
        repository.save(new AnalistaDeSistema("joão", new BigDecimal("4000")));
        repository.save(new DepartamentoPessoal("josé", new BigDecimal("3000")));
        repository.save(new DepartamentoPessoal("ana", new BigDecimal("2500")));
        repository.save(new DepartamentoPessoal("paula", new BigDecimal("1000")));
    }
}
