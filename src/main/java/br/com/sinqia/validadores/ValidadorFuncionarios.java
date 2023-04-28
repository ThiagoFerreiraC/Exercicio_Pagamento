package br.com.sinqia.validadores;

import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.cargos.Funcionario;

import java.util.List;

public interface ValidadorFuncionarios extends Validador<List<Funcionario>> {

    @Override
    void validar(List<Funcionario> funcionarios);
}
