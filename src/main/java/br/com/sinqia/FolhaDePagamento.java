package br.com.sinqia;

import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.exceptions.SalarioNotFoundException;

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

        if (salario == null || salario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new SalarioNotFoundException();
            //TODO salario is blank ou algo similar
        }

        if (salario.compareTo(BigDecimal.ZERO) <= 0) {

        }

        //TODO limites de alíquotas, taxas e descontos não podem ser inferiores a zero



        Aliquota aliquotaParaSalario = aliquotas.stream()
                .filter(aliquota -> salario.compareTo(aliquota.getLimiteInferior()) > 0)
                .max(Comparator.comparing(Aliquota::getLimiteInferior))
                .get();


        BigDecimal impostoTotal = aliquotaParaSalario.getTaxaIRRF().multiply(salario);
        BigDecimal impostoMenosDesconto = impostoTotal.subtract(aliquotaParaSalario.getDesconto());

        return salario.subtract(impostoMenosDesconto);
    }
}
