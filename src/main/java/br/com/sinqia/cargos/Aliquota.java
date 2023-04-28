package br.com.sinqia.cargos;

import java.math.BigDecimal;

public class Aliquota {
    private final BigDecimal limiteInferior;
    private final BigDecimal taxaIRRF;
    private final BigDecimal desconto;

    public Aliquota(BigDecimal limiteInferior, BigDecimal taxaIRRF, BigDecimal desconto) {
        this.limiteInferior = limiteInferior;
        this.taxaIRRF = taxaIRRF;
        this.desconto = desconto;
    }

    public BigDecimal getLimiteInferior() {
        return limiteInferior;
    }

    public BigDecimal getTaxaIRRF() {
        return taxaIRRF;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }
}
