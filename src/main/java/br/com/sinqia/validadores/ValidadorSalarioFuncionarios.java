package br.com.sinqia.validadores;

import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.exceptions.SalarioInvalidException;

import java.math.BigDecimal;
import java.util.List;

public class ValidadorSalarioFuncionarios implements ValidadorFuncionarios{
    @Override
    public void validar(List<Funcionario> funcionarios) {
        if (funcionarios.stream().anyMatch(funcionario ->
                funcionario.getSalario().compareTo(BigDecimal.ZERO) <= 0)) {
            throw new SalarioInvalidException();
        }
    }
}
