package br.com.sinqia.validadores;

import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.exceptions.AliquotaInvalidException;

import java.math.BigDecimal;
import java.util.List;

public class ValidadorValoresDasAliquotas implements ValidadorAliquotas{
    @Override
    public void validar(List<Aliquota> aliquotas) {
        boolean valoresNasAliquotasInvalidos = aliquotas.stream().anyMatch(aliquota -> aliquota.getLimiteInferior().compareTo(BigDecimal.ZERO) < 0
                || aliquota.getDesconto().compareTo(BigDecimal.ZERO) < 0
                || aliquota.getTaxaIRRF().compareTo(BigDecimal.ZERO) < 0);

        if (valoresNasAliquotasInvalidos) {
            throw new AliquotaInvalidException();
        }
    }
}
