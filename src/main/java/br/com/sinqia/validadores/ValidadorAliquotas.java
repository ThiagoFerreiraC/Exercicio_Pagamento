package br.com.sinqia.validadores;

import br.com.sinqia.cargos.Aliquota;

import java.util.List;

public interface ValidadorAliquotas extends Validador<List<Aliquota>> {
    @Override
    void validar(List<Aliquota> aliquotas);
}
