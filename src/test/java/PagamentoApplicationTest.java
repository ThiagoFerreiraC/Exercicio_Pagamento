import br.com.sinqia.*;

import br.com.sinqia.exceptions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PagamentoApplicationTest {
    private PagamentoApplication pagamentoApplication;
    @BeforeEach
    public void inicializar() {
        this.pagamentoApplication = new PagamentoApplication();
    }

    @Test
    public void dadoGeradorDeDadosNullDeveGerarException() {
        Assertions.assertThrows(GeradorDeDadosNotFoundException.class, () -> {
            pagamentoApplication.gerarDados(null);
        });
    }

    @Test
    public void dadoListaDeAliquotasNulaDeveGerarException() {
        Assertions.assertThrowsExactly(AliquotasNullException.class, () -> {
            pagamentoApplication.gerarCalculadoraIRRF(null);
        });
    }

    @Test
    public void dadoCalculadoraIRRFNulaDeveGerarException() {
        Assertions.assertThrowsExactly(CalculadoraIRRFNullException.class, () -> {
            pagamentoApplication.gerarProcessadorDePagamento(null);
        });
    }
    @Test
    public void dadoRepositoryNuloDeveGerarException() {
        Assertions.assertThrowsExactly(RepositoryNotFoundException.class, () -> {
            pagamentoApplication.findAll(null);
        });
    }

    @Test
    public void dadoFuncionarioGerarStringNomeESalarioProcessadoComSucesso() {

        Map<String, BigDecimal> nomeFuncionarioESalarioProcessadoMapa = Map.of("funcionarioTeste", new BigDecimal("4494.30"));

        List<String> nomeESalarioProcessados = pagamentoApplication.gerarListaDeNomeESalarioProcessadoFuncionario(nomeFuncionarioESalarioProcessadoMapa);

        Assertions.assertEquals(nomeESalarioProcessados.get(0), "FUNCIONARIOTESTE = 4494.30");
    }
}
