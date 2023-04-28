package validadores;

import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.exceptions.AliquotaInvalidException;
import br.com.sinqia.repositories.AliquotaRepository;
import br.com.sinqia.validadores.ValidadorAliquotas;
import br.com.sinqia.validadores.ValidadorValoresDasAliquotas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ValidadorValoresDasAliquotasTeste {

    @Test
    public void dadoLimiteInferiorNegativoDeveGerarException() {
        AliquotaRepository repository = new AliquotaRepository();
        repository.save(new Aliquota(new BigDecimal("-50"), new BigDecimal("100"), new BigDecimal("100")));
        ValidadorAliquotas validadorAliquotas = new ValidadorValoresDasAliquotas();
        Assertions.assertThrowsExactly(AliquotaInvalidException.class, () -> {validadorAliquotas.})
    }
}
