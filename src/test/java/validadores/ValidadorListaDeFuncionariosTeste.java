package validadores;

import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.exceptions.FuncionariosNullException;
import br.com.sinqia.validadores.ValidadorFuncionarios;
import br.com.sinqia.validadores.ValidadorListaDeFuncionarios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ValidadorListaDeFuncionariosTeste {
    private ValidadorFuncionarios validarFuncionarios;

    @BeforeEach
    public void inicializar() {
        this.validarFuncionarios = new ValidadorListaDeFuncionarios();
    }

    @Test
    public void dadoListaDeFuncionariosNulaDeveGerarException() {
        Assertions.assertThrowsExactly(FuncionariosNullException.class, () -> {
            validarFuncionarios.validar(null);
        });
    }

    @Test
    public void dadoListaDeFuncionariosVaziaDeveGerarException() {
        List<Funcionario> funcionarios = new ArrayList<>();
        Assertions.assertThrowsExactly(FuncionariosNullException.class, () -> {
            validarFuncionarios.validar(funcionarios);
        });
    }

}
