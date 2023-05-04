package br.com.sinqia;

import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.validadores.ValidadorAliquotas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

public class CalculadoraIRRF {
    private final List<Aliquota> aliquotas;
    private final ValidadorAliquotas validadorValoresDasAliquotas;

    public CalculadoraIRRF(List<Aliquota> aliquotas, ValidadorAliquotas validadorValoresDasAliquotas) {
        this.aliquotas = aliquotas;
        this.validadorValoresDasAliquotas = validadorValoresDasAliquotas;
    }

    public BigDecimal calcularImposto(BigDecimal salario) {
        Aliquota aliquotaParaSalario = pegarAliquotaCorrespondente(salario);
        BigDecimal impostoTotal = aliquotaParaSalario.getTaxaIRRF().multiply(salario);
        return impostoTotal.subtract(aliquotaParaSalario.getDesconto()).setScale(2, RoundingMode.CEILING);
    }

    private Aliquota pegarAliquotaCorrespondente(BigDecimal salario) {
        validadorValoresDasAliquotas.validar(aliquotas);
        return aliquotas.stream()
                .filter(aliquota -> salario.compareTo(aliquota.getLimiteInferior()) > 0)
                .max(Comparator.comparing(Aliquota::getLimiteInferior))
                .get();
    }
}
