import br.com.sinqia.FolhaDePagamento;
import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.Pagamento;
import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.exceptions.*;
import br.com.sinqia.repositories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PagamentoTest {
    //TODO assertions de acertos

    @Test
    public void dadoRepositorioNullDeveGerarException() {
        Pagamento pagamento = new Pagamento();
        Assertions.assertThrows(GeradorDeDadosNotFoundException.class, () -> {pagamento.gerarDados(null);});
    }

    @Test
    public void dadoListaDeAliquotasNulaDeveGerarException() {
        Pagamento pagamento = new Pagamento();
        Assertions.assertThrowsExactly(AliquotasNullException.class, () -> {pagamento.gerarFolhaDePagamento(null);});
    }

    @Test
    public void dadoListaDeFuncionariosNulaDeveGerarException() {
        Pagamento pagamento = new Pagamento();
        Repository<Aliquota> aliquotaRepository = new AliquotaRepository();
        pagamento.gerarDados(new AliquotaGeradorDeDados(aliquotaRepository));
        List<Aliquota> aliquotas =pagamento.findAll(aliquotaRepository);
        FolhaDePagamento folhaDePagamento = pagamento.gerarFolhaDePagamento(aliquotas);
        Assertions.assertThrowsExactly(FuncionariosNullException.class, () -> {pagamento.processarSalarioFuncionario(null, folhaDePagamento);});
    }

    @Test
    public void dadoFolhaDePagamentoNulaDeveGerarException() {
        Pagamento pagamento = new Pagamento();
        Repository<Funcionario> funcionarioRepository = new FuncionarioRepository();
        pagamento.gerarDados(new FuncionarioGeradorDeDados(funcionarioRepository));
        List<Funcionario> funcionarios = pagamento.findAll(funcionarioRepository);

        Assertions.assertThrowsExactly(FolhaDePagamentoNullException.class, () -> {pagamento.processarSalarioFuncionario(funcionarios, null);});
    }

    @Test
    public void dadoFuncionarioSemNomeDeveGerarException() {
        Pagamento pagamento = new Pagamento();
        Repository<Aliquota> aliquotaRepository = new AliquotaRepository();
        pagamento.gerarDados(new AliquotaGeradorDeDados(aliquotaRepository));
        List<Aliquota> aliquotas = pagamento.findAll(aliquotaRepository);

        FolhaDePagamento folhaDePagamento = pagamento.gerarFolhaDePagamento(aliquotas);

        Repository<Funcionario> funcionarioRepository = new FuncionarioRepository();
        pagamento.gerarDados(new FuncionarioGeradorDeDados(funcionarioRepository));
        List<Funcionario> funcionarios = pagamento.findAll(funcionarioRepository);

        Assertions.assertThrowsExactly(FuncionarioNomeBlankException.class, () -> {pagamento.processarSalarioFuncionario(funcionarios, folhaDePagamento);});
    }

}
