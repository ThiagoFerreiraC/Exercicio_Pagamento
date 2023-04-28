package br.com.sinqia.validadores;

import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.exceptions.FuncionariosNullException;

import java.util.List;

public class ValidadorListaDeFuncionarios implements ValidadorFuncionarios{
    @Override
    public void validar(List<Funcionario> funcionarios) {
        if (funcionarios == null || funcionarios.isEmpty()) {
            throw new FuncionariosNullException("Funcionários não encontrados");
        }
    }
}
