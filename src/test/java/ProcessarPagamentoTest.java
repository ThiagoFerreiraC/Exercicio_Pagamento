import br.com.sinqia.*;
import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.exceptions.FolhaDePagamentoNullException;import br.com.sinqia.geradores.AliquotaGeradorDeDados;
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

import java.util.List;

public class ProcessarPagamentoTest {
    private Pagamento pagamento;
    private List<Funcionario> funcionarios;
    private List<Aliquota> aliquotas;
    private CalculadoraIRRF calculadoraIRRF;
    private ProcessarPagamento processarPagamento;
    @BeforeEach
    public void inicilizar() {
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
        this.processarPagamento = new ProcessarPagamento(calculadoraIRRF,
                List.of(new ValidadorListaDeFuncionarios(),
                        new ValidadorNomeFuncionarios(),
                        new ValidadorSalarioFuncionarios()));
    }

}
