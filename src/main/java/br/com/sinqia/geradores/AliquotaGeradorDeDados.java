package br.com.sinqia.geradores;

import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.repositories.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;

public class AliquotaGeradorDeDados implements GeradorDeDados {

    private final Repository<Aliquota> repository;

    public AliquotaGeradorDeDados(Repository<Aliquota> repository) {
        this.repository = repository;
    }

    @Override
    public void gerarDados() {
        repository.save(new Aliquota(new BigDecimal(BigInteger.ZERO), new BigDecimal(BigInteger.ZERO), new BigDecimal(BigInteger.ZERO)));
        repository.save(new Aliquota(new BigDecimal("1903.99"), new BigDecimal("0.075"), new BigDecimal("142.80")));
        repository.save(new Aliquota(new BigDecimal("2826.66"), new BigDecimal("0.15"), new BigDecimal("354.80")));
        repository.save(new Aliquota(new BigDecimal("3751.06"), new BigDecimal("0.225"), new BigDecimal("636.16")));
        repository.save(new Aliquota(new BigDecimal("4664.68"), new BigDecimal("0.275"), new BigDecimal("869.30")));
    }
}
