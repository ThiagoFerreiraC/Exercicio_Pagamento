package validadores;

import br.com.sinqia.CalculadoraIRRF;
import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.validadores.ValidadorAliquotas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class CalculadoraIRRFTeste {

    private CalculadoraIRRF calculadoraIRRF;

    @Test
    public void dadoSalarioMaiorQueQuartaFaixaRetornaImposto() {
        Aliquota aliquota = new Aliquota(new BigDecimal("4664.68"), new BigDecimal("0.275"), new BigDecimal("869.30"));
        this.calculadoraIRRF = new CalculadoraIRRF(List.of(aliquota), new ValidadorAliquotaMock());

        BigDecimal imposto = calculadoraIRRF.calcularImposto(new BigDecimal("5000"));
        Assertions.assertEquals(new BigDecimal("505.70"), imposto);
    }

    @Test
    public void dadoSalarioMaiorQueTerceiraFaixaRetornaImposto() {
        Aliquota aliquota = new Aliquota(new BigDecimal("3751.06"), new BigDecimal("0.225"), new BigDecimal("636.16"));
        this.calculadoraIRRF = new CalculadoraIRRF(List.of(aliquota), new ValidadorAliquotaMock());

        BigDecimal imposto = calculadoraIRRF.calcularImposto(new BigDecimal("4000"));
        Assertions.assertEquals(new BigDecimal("263.84"), imposto);
    }

    @Test
    public void dadoSalarioMaiorQueSegundaFaixaRetornaImposto() {
        Aliquota aliquota = new Aliquota(new BigDecimal("2826.66"), new BigDecimal("0.15"), new BigDecimal("354.80"));
        this.calculadoraIRRF = new CalculadoraIRRF(List.of(aliquota), new ValidadorAliquotaMock());

        BigDecimal imposto = calculadoraIRRF.calcularImposto(new BigDecimal("3000"));
        Assertions.assertEquals(new BigDecimal("95.20"), imposto);
    }

    @Test
    public void dadoSalarioMaiorQuePrimeiraFaixaRetornaImposto() {
        Aliquota aliquota = new Aliquota(new BigDecimal("1903.99"), new BigDecimal("0.075"), new BigDecimal("142.80"));
        this.calculadoraIRRF = new CalculadoraIRRF(List.of(aliquota), new ValidadorAliquotaMock());

        BigDecimal imposto = calculadoraIRRF.calcularImposto(new BigDecimal("2500"));
        Assertions.assertEquals(new BigDecimal("44.70"), imposto);
    }

    @Test
    public void dadoSalarioIsentoDeImpostoRetornarZero() {
        Aliquota aliquota =  new Aliquota(new BigDecimal(BigInteger.ZERO), new BigDecimal(BigInteger.ZERO), new BigDecimal(BigInteger.ZERO));
        this.calculadoraIRRF = new CalculadoraIRRF(List.of(aliquota), new ValidadorAliquotaMock());

        BigDecimal imposto = calculadoraIRRF.calcularImposto(new BigDecimal("1000"));
        Assertions.assertEquals(new BigDecimal("0.00"), imposto);
    }
}

class ValidadorAliquotaMock implements ValidadorAliquotas {

    @Override
    public void validar(List<Aliquota> aliquotas) {

    }
}
