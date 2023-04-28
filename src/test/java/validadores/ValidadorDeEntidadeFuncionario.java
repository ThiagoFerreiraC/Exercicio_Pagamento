package validadores;

import br.com.sinqia.cargos.Desenvolvedor;
import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.exceptions.FuncionarioNotFoundException;
import br.com.sinqia.repositories.AliquotaRepository;
import br.com.sinqia.repositories.FuncionarioRepository;
import br.com.sinqia.validadores.ValidadorFuncionarios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class ValidadorDeEntidadeFuncionario {

    private ValidadorFuncionarios validarFuncionarios;

    @BeforeEach
    public void inicializar() {
        this.validarFuncionarios = new br.com.sinqia.validadores.ValidadorDeEntidadeFuncionario();
    }

    @Test
    public void dadoFuncionarioNuloDeveGerarException() {
        FuncionarioRepository repository = new FuncionarioRepository();
        repository.save(null);
        List<Funcionario> funcionarios = repository.findAll();
        Assertions.assertThrowsExactly(FuncionarioNotFoundException.class, () -> {validarFuncionarios.validar(funcionarios);
        });
    }
}
