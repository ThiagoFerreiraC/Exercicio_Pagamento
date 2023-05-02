package validadores;

import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.exceptions.AliquotaInvalidException;
import br.com.sinqia.repositories.AliquotaRepository;
import br.com.sinqia.validadores.ValidadorAliquotas;
import br.com.sinqia.validadores.ValidadorValoresDasAliquotas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class ValidadorValoresDasAliquotasTeste {

    private ValidadorAliquotas validadorAliquotas;

    @BeforeEach
    public void inicializar() {
        this.validadorAliquotas = new ValidadorValoresDasAliquotas();
    }

    @Test
    public void dadoLimiteInferiorNegativoDeveGerarException() {
        List<Aliquota> aliquotas = List.of(new Aliquota(
                new BigDecimal("-5"),
                new BigDecimal("100"),
                new BigDecimal("100")));
        Assertions.assertThrowsExactly(AliquotaInvalidException.class, () -> {validadorAliquotas.validar(aliquotas);});
    }

    @Test
    public void dadoTaxaNegativaDeveGerarException() {
        List<Aliquota> aliquotas = List.of(new Aliquota(
                new BigDecimal("100"),
                new BigDecimal("-5"),
                new BigDecimal("100")));
        Assertions.assertThrowsExactly(AliquotaInvalidException.class, () -> {validadorAliquotas.validar(aliquotas);});
    }

    @Test
    public void dadoDescontoNegativoDeveGerarException() {
        List<Aliquota> aliquotas = List.of(new Aliquota(
                new BigDecimal("100"),
                new BigDecimal("100"),
                new BigDecimal("-5")));
        Assertions.assertThrowsExactly(AliquotaInvalidException.class, () -> {validadorAliquotas.validar(aliquotas);});
    }
}
