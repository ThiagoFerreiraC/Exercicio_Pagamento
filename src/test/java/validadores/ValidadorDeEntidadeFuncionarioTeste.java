package validadores;

import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.exceptions.FuncionarioNotFoundException;
import br.com.sinqia.validadores.ValidadorFuncionarios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ValidadorDeEntidadeFuncionarioTeste {

    private ValidadorFuncionarios validarFuncionarios;

    @BeforeEach
    public void inicializar() {
        this.validarFuncionarios = new br.com.sinqia.validadores.ValidadorDeEntidadeFuncionario();
    }

    @Test
    public void dadoFuncionarioNuloDeveGerarException() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(null);
        Assertions.assertThrowsExactly(FuncionarioNotFoundException.class, () -> {validarFuncionarios.validar(funcionarios);
        });
    }
}
