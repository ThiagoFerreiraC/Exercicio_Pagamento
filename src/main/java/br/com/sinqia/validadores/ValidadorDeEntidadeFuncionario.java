package br.com.sinqia.validadores;

import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.exceptions.FuncionarioNotFoundException;

import java.util.List;
import java.util.Objects;

public class ValidadorDeEntidadeFuncionario implements ValidadorFuncionarios {
    @Override
    public void validar(List<Funcionario> funcionarios) {
        if (funcionarios.stream().anyMatch(Objects::isNull)) {
            throw new FuncionarioNotFoundException("Funcionário não encontrado");
            //TODO passar todas as mensagens para dentro das exceptions
        }
    }
}
