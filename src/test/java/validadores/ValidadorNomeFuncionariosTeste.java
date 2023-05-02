package validadores;

import br.com.sinqia.cargos.Desenvolvedor;
import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.exceptions.FuncionarioNotFoundException;
import br.com.sinqia.validadores.ValidadorFuncionarios;
import br.com.sinqia.validadores.ValidadorNomeFuncionarios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class ValidadorNomeFuncionariosTeste {

    private ValidadorFuncionarios validadorFuncionarios;

    @BeforeEach
    public void inicializar() {
        this.validadorFuncionarios = new ValidadorNomeFuncionarios();
    }

    @Test
    public void dadoFuncionarioNomeNuloDeveGerarException() {
        List<Funcionario> funcionarios = List.of(new Desenvolvedor(null, new BigDecimal("5000")));

        Assertions.assertThrowsExactly(FuncionarioNotFoundException.class, () -> {
            validadorFuncionarios.validar(funcionarios);
        });
    }

    @Test
    public void dadoFuncionarioNomeVazioDeveGerarException() {
        List<Funcionario> funcionarios = List.of(new Desenvolvedor("", new BigDecimal("5000")));

        Assertions.assertThrowsExactly(FuncionarioNotFoundException.class, () -> {
            validadorFuncionarios.validar(funcionarios);
        });
    }

}
