package br.com.sinqia.repositories;

import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.cargos.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository implements Repository<Funcionario> {

    private final List<Funcionario> funcionarios = new ArrayList<>();

    @Override
    public List<Funcionario> findAll() {
        return funcionarios;
    }

    @Override
    public void save(Funcionario dado) {
        funcionarios.add(dado);
    }

}
