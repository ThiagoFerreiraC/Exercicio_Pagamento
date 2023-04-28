package br.com.sinqia.validadores;

import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.exceptions.FuncionarioNotFoundException;

import java.util.List;

public class ValidadorNomeFuncionarios implements ValidadorFuncionarios{
    @Override
    public void validar(List<Funcionario> funcionarios) {
        if (funcionarios.stream().anyMatch(funcionario ->
                funcionario.getNome() == null || funcionario.getNome().isBlank())) {
            throw new FuncionarioNotFoundException("Nome do funcionário não encontrado");
            //TODO passar todas as mensagens para dentro das exceptions
        }
    }
}
