package validadores;

import br.com.sinqia.CalculadoraIRRF;
import br.com.sinqia.ProcessarPagamento;
import br.com.sinqia.cargos.Aliquota;
import br.com.sinqia.cargos.Desenvolvedor;
import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.validadores.ValidadorFuncionarios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ProcessarPagamentoTesteApplication {


    @Test
    public void dadoListaFuncionariosDeveRetornarMapaNomeESalarioProcessado() {
        Aliquota aliquota = new Aliquota(new BigDecimal("4664.68"), new BigDecimal("0.275"), new BigDecimal("869.30"));
        CalculadoraIRRF calculadoraIRRF = new CalculadoraIRRF(List.of(aliquota), new ValidadorAliquotaMock());

        ProcessarPagamento processarPagamento = new ProcessarPagamento(calculadoraIRRF, List.of(new ValidadorFuncionarioMock()));
        Map<String, BigDecimal> mapaTeste = processarPagamento.processarSalarioFuncionario(List.of(new Desenvolvedor("FuncionarioTeste", new BigDecimal("5000"))));

        Assertions.assertEquals(mapaTeste.get("FuncionarioTeste"), new BigDecimal("4494.30"));
    }
}

class ValidadorFuncionarioMock implements ValidadorFuncionarios {

    @Override
    public void validar(List<Funcionario> funcionarios) {

    }
}