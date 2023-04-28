package br.com.sinqia;

import br.com.sinqia.cargos.Funcionario;
import br.com.sinqia.exceptions.*;
import br.com.sinqia.validadores.Validador;
import br.com.sinqia.validadores.ValidadorFuncionarios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProcessarPagamento {
    private final CalculadoraIRRF calculadoraIRRF;

    private final List<ValidadorFuncionarios> validadoresParaFuncionarios;

    public ProcessarPagamento(CalculadoraIRRF calculadoraIRRF,
                              List<ValidadorFuncionarios> validadoresParaFuncionarios) {
        this.calculadoraIRRF = calculadoraIRRF;
        this.validadoresParaFuncionarios = validadoresParaFuncionarios;
    }

    public Map<String, BigDecimal> processarSalarioFuncionario(List<Funcionario> funcionarios) {
        validadoresParaFuncionarios.iterator().next().validar(funcionarios);

        return funcionarios
                .stream()
                .collect(Collectors.toMap(
                        Funcionario::getNome,
                        this::descontarImpostoDoSalario
                ));
    }
    public BigDecimal descontarImpostoDoSalario(Funcionario funcionario) {
        BigDecimal salario = funcionario.getSalario();
        BigDecimal imposto = calculadoraIRRF.calcularImposto(salario);
        return salario.subtract(imposto).setScale(2, RoundingMode.CEILING);
    }
}
