package validadores;

import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.cargos.Desenvolvedor;
import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.exceptions.AliquotaInvalidException;
import br.com.sinqia.exceptions.SalarioInvalidException;
import br.com.sinqia.validadores.ValidadorFuncionarios;
import br.com.sinqia.validadores.ValidadorSalarioFuncionarios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class ValidadorSalarioFuncionariosTeste {

    private ValidadorFuncionarios validadorFuncionarios;

    @BeforeEach
    public void inicializar() {
        this.validadorFuncionarios = new ValidadorSalarioFuncionarios();
    }

    @Test
    public void dadoSalarioNegativoDeveGerarException() {
        List<Funcionario> aliquotas = List.of(new Desenvolvedor("funcionarioTeste", new BigDecimal("-10")));
        Assertions.assertThrowsExactly(SalarioInvalidException.class, () -> {validadorFuncionarios.validar(aliquotas);});
    }

    @Test
    public void dadoSalarioIgualAZeroDeveGerarException() {
        List<Funcionario> aliquotas = List.of(new Desenvolvedor("funcionarioTeste", new BigDecimal("0")));
        Assertions.assertThrowsExactly(SalarioInvalidException.class, () -> {validadorFuncionarios.validar(aliquotas);});
    }
}
