import br.com.sinqia.*;
import br.com.sinqia.cargos.Desenvolvedor;
import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.exceptions.*;
import br.com.sinqia.repositories.AliquotaRepository;
import br.com.sinqia.repositories.FuncionarioRepository;
import br.com.sinqia.repositories.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class PagamentoTest {
    //TODO assertions de acertos

    private Pagamento pagamento;
    private List<Funcionario> funcionarios;

    private List<Aliquota> aliquotas;

    private FolhaDePagamento folhaDePagamento;

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

        this.folhaDePagamento = pagamento.gerarFolhaDePagamento(aliquotas);
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
            pagamento.gerarFolhaDePagamento(null);
        });
    }

    @Test
    public void dadoListaDeFuncionariosNulaDeveGerarException() {
        Assertions.assertThrowsExactly(FuncionariosNullException.class, () -> {
            pagamento.processarSalarioFuncionario(null, folhaDePagamento);
        });
    }

    @Test
    public void dadoFolhaDePagamentoNulaDeveGerarException() {
        Assertions.assertThrowsExactly(FolhaDePagamentoNullException.class, () -> {
            pagamento.processarSalarioFuncionario(funcionarios, null);
        });
    }

    @Test
    public void dadoFuncionarioNomeVazioDeveGerarException() {
        List<Funcionario> funcionarios = List.of(new Desenvolvedor("", new BigDecimal("5000")));

        Assertions.assertThrowsExactly(FuncionarioNotFoundException.class, () -> {
            pagamento.processarSalarioFuncionario(funcionarios, folhaDePagamento);
        });
    }

    @Test
    public void dadoFuncionarioNuloDeveGerarException() {
        List<Funcionario> funcionarios = List.of(new Desenvolvedor(null, new BigDecimal("5000")));

        Assertions.assertThrowsExactly(FuncionarioNotFoundException.class, () -> {
            pagamento.processarSalarioFuncionario(funcionarios, folhaDePagamento);
        });
    }

    @Test
    public void dadoFuncionarioNomeNuloDeveGerarException() {
        List<Funcionario> funcionarios = List.of(new Desenvolvedor(null, new BigDecimal("5000")));

        Assertions.assertThrowsExactly(FuncionarioNotFoundException.class, () -> {
            pagamento.processarSalarioFuncionario(funcionarios, folhaDePagamento);
        });
    }

    @Test
    public void dadoFuncionarioGerarMapaNomeESalarioProcessadoComSucesso() {
        Funcionario funcionario = new Desenvolvedor("funcionarioTeste", new BigDecimal("5000"));

        Map<String, BigDecimal> nomeFuncionarioESalarioProcessadoMapa = pagamento.processarSalarioFuncionario(List.of(funcionario),
                folhaDePagamento);

        Assertions.assertEquals(nomeFuncionarioESalarioProcessadoMapa.get("funcionarioTeste"),
                new BigDecimal("4494.300"));
        //TODO talvez colocar o cálculo do salário processado aqui
    }

    @Test
    public void dadoFuncionarioImprimirNomeESalarioProcessadoComSucesso() {
        List<Funcionario> funcionarios = List.of(new Desenvolvedor("funcionarioTeste", new BigDecimal("5000")));

        Map<String, BigDecimal> nomeFuncionarioESalarioProcessadoMapa = pagamento.processarSalarioFuncionario(funcionarios, folhaDePagamento);

        List<String> nomeESalarioProcessados = pagamento.gerarListaDeNomeESalarioProcessadoFuncionario(nomeFuncionarioESalarioProcessadoMapa);

        Assertions.assertEquals(nomeESalarioProcessados.get(0), "FUNCIONARIOTESTE = 4494.300");
    }
}
