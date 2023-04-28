package br.com.sinqia.repositories;

import br.com.sinqia.cargos.Aliquota;

import java.util.ArrayList;
import java.util.List;

public class AliquotaRepository implements Repository{
    private List<Aliquota> aliquotas = new ArrayList<>();

    @Override
    public List<Aliquota> findAll() {
        return aliquotas;
    }

    @Override
    public void save(Object dado) {
        aliquotas.add((Aliquota) dado);
    }
}
