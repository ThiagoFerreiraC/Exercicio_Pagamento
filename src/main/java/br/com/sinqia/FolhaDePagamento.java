package br.com.sinqia;

import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.exceptions.AliquotaInvalidException;
import br.com.sinqia.exceptions.SalarioInvalidException;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class FolhaDePagamento {
    private final List<Aliquota> aliquotas;

    public FolhaDePagamento(List<Aliquota> aliquotas) {
        this.aliquotas = aliquotas;
    }

    public BigDecimal processarSalario(Funcionario funcionario) {
        BigDecimal salario = funcionario.getSalario();

        if (salario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new SalarioInvalidException();
        }


        Aliquota aliquotaParaSalario = pegarAliquotaCorrespondente(salario);

        BigDecimal impostoTotal = aliquotaParaSalario.getTaxaIRRF().multiply(salario);
        BigDecimal impostoMenosDesconto = impostoTotal.subtract(aliquotaParaSalario.getDesconto());

        return salario.subtract(impostoMenosDesconto);
    }

    boolean valorPresenteNasAliquotasInvalido() {
        return aliquotas.stream().anyMatch(aliquota -> aliquota.getLimiteInferior().compareTo(BigDecimal.ZERO) < 0
                        || aliquota.getDesconto().compareTo(BigDecimal.ZERO) < 0
                        || aliquota.getTaxaIRRF().compareTo(BigDecimal.ZERO) < 0);
    }

    private Aliquota pegarAliquotaCorrespondente(BigDecimal salario) {

        if (valorPresenteNasAliquotasInvalido()) {
            throw new AliquotaInvalidException();
        }

        return  aliquotas.stream()
                .filter(aliquota -> salario.compareTo(aliquota.getLimiteInferior()) > 0)
                .max(Comparator.comparing(Aliquota::getLimiteInferior))
                .get();
    }
}
