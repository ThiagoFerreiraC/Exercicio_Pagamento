import br.com.sinqia.*;
import br.com.sinqia.cargos.Desenvolvedor;
import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.exceptions.*;
import br.com.sinqia.geradores.AliquotaGeradorDeDados;
import br.com.sinqia.geradores.FuncionarioGeradorDeDados;
import br.com.sinqia.repositories.AliquotaRepository;
import br.com.sinqia.repositories.FuncionarioRepository;
import br.com.sinqia.repositories.Repository;
import br.com.sinqia.validadores.ValidadorListaDeFuncionarios;
import br.com.sinqia.validadores.ValidadorNomeFuncionarios;
import br.com.sinqia.validadores.ValidadorSalarioFuncionarios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PagamentoTest {
    private Pagamento pagamento;
    private List<Funcionario> funcionarios;
    private List<Aliquota> aliquotas;
    private CalculadoraIRRF calculadoraIRRF;
    @BeforeEach
    public void inicializar() {
        this.pagamento = new Pagamento();

        //--------Funcionários
        Repository<Funcionario> funcionarioRepository = new FuncionarioRepository();
        pagamento.gerarDados(new FuncionarioGeradorDeDados(funcionarioRepository));
        this.funcionarios = pagamento.findAll(funcionarioRepository);

        //--------Alíquotas
        Repository<Aliquota> aliquotaRepository = new AliquotaRepository();
        pagamento.gerarDados(new AliquotaGeradorDeDados(aliquotaRepository));
        this.aliquotas = pagamento.findAll(aliquotaRepository);

        this.calculadoraIRRF = pagamento.gerarCalculadoraIRRF(aliquotas);
    }

    @Test
    public void dadoGeradorDeDadosNullDeveGerarException() {
        Assertions.assertThrows(GeradorDeDadosNotFoundException.class, () -> {
            pagamento.gerarDados(null);
        });
    }

    @Test
    public void dadoListaDeAliquotasNulaDeveGerarException() {
        Assertions.assertThrowsExactly(AliquotasNullException.class, () -> {
            pagamento.gerarCalculadoraIRRF(null);
        });
    }

    @Test
    public void dadoFuncionarioGerarMapaNomeESalarioProcessadoComSucesso() {
        ProcessarPagamento processarPagamento = new ProcessarPagamento(calculadoraIRRF,
                List.of(new ValidadorListaDeFuncionarios(),
                        new ValidadorNomeFuncionarios(),
                        new ValidadorSalarioFuncionarios()));

        Map<String, BigDecimal> nomeFuncionarioESalarioProcessadoMapa = processarPagamento.processarSalarioFuncionario(funcionarios);

        Assertions.assertEquals(
                nomeFuncionarioESalarioProcessadoMapa.get("joão"), new BigDecimal("3736.16"));
        Assertions.assertEquals(
                nomeFuncionarioESalarioProcessadoMapa.get("paula"), new BigDecimal("1000.00"));
        Assertions.assertEquals(
                nomeFuncionarioESalarioProcessadoMapa.get("ana"), new BigDecimal("2455.30"));
        Assertions.assertEquals(
                nomeFuncionarioESalarioProcessadoMapa.get("josé"), new BigDecimal("2904.80"));
        Assertions.assertEquals(
                nomeFuncionarioESalarioProcessadoMapa.get("maria"), new BigDecimal("4494.30"));
    }

    @Test
    public void dadoFuncionarioGerarStringNomeESalarioProcessadoComSucesso() {
        List<Funcionario> funcionarios = List.of(new Desenvolvedor("funcionarioTeste", new BigDecimal("5000")));

        ProcessarPagamento processarPagamento = new ProcessarPagamento(calculadoraIRRF,
                List.of(new ValidadorListaDeFuncionarios()));

        Map<String, BigDecimal> nomeFuncionarioESalarioProcessadoMapa = processarPagamento.processarSalarioFuncionario(funcionarios);

        List<String> nomeESalarioProcessados = pagamento.gerarListaDeNomeESalarioProcessadoFuncionario(nomeFuncionarioESalarioProcessadoMapa);

        Assertions.assertEquals(nomeESalarioProcessados.get(0), "FUNCIONARIOTESTE = 4494.30");
    }
}
