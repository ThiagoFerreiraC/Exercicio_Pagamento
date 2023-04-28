package br.com.sinqia.validadores;

import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.exceptions.AliquotaInvalidException;
import br.com.sinqia.exceptions.AliquotasNullException;

import java.util.List;

public class ValidarListaDeAliquotas implements ValidadorAliquotas {
    @Override
    public void validar(List<Aliquota> aliquotas) {
        if (aliquotas == null || aliquotas.isEmpty()) {
            throw new AliquotasNullException("Alíquotas não encontradas");
        }
    }
}
