package br.com.sinqia;

import br.com.sinqia.cargos.*;
import br.com.sinqia.exceptions.*;
import br.com.sinqia.geradores.AliquotaGeradorDeDados;
import br.com.sinqia.geradores.FuncionarioGeradorDeDados;
import br.com.sinqia.geradores.GeradorDeDados;
import br.com.sinqia.repositories.*;
import br.com.sinqia.validadores.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Pagamento {

    public static void main(String[] args) {
        Pagamento pagamento = new Pagamento();

        //--------Funcionários -------
        Repository<Funcionario> funcionarioRepository = new FuncionarioRepository();
        pagamento.gerarDados(new FuncionarioGeradorDeDados(funcionarioRepository));
        List<Funcionario> funcionarios = pagamento.findAll(funcionarioRepository);

        //--------Alíquotas
        Repository<Aliquota> aliquotaRepository = new AliquotaRepository();
        pagamento.gerarDados(new AliquotaGeradorDeDados(aliquotaRepository));
        List<Aliquota> aliquotas = pagamento.findAll(aliquotaRepository);

        CalculadoraIRRF calculadoraIRRF = pagamento.gerarCalculadoraIRRF(aliquotas);

        ProcessarPagamento processarPagamento = pagamento.gerarProcessadorDePagamento(calculadoraIRRF);

        Map<String, BigDecimal> nomeFuncionarioESalarioProcessadoMapa = processarPagamento.processarSalarioFuncionario(funcionarios);

        List<String> nomeESalarioProcessados = pagamento.gerarListaDeNomeESalarioProcessadoFuncionario(nomeFuncionarioESalarioProcessadoMapa);

        nomeESalarioProcessados.forEach(System.out::println);
    }

    public void gerarDados(GeradorDeDados geradorDeDados) {
        if (geradorDeDados == null) {
            throw new GeradorDeDadosNotFoundException("Gerador de dados não encontrado");
        }
        geradorDeDados.gerarDados();
    }

    public <T> List<T> findAll(Repository repository) {
        if (repository == null) {
            throw new RepositoryNotFoundException("Repositório não encontrado");
        }
        return repository.findAll();
    }

    public CalculadoraIRRF gerarCalculadoraIRRF(List<Aliquota> aliquotas) {
        ValidadorAliquotas validadorAliquotas = new ValidarListaDeAliquotas();
        validadorAliquotas.validar(aliquotas);
        return new CalculadoraIRRF(aliquotas, new ValidarValoresDasAliquotas());
    }

    public ProcessarPagamento gerarProcessadorDePagamento(CalculadoraIRRF calculadoraIRRF) {
        if (calculadoraIRRF == null) {
            throw new AliquotasNullException("Alíquotas não encontradas");
            //TODO Trocar Exception
        }
        return new ProcessarPagamento(calculadoraIRRF,
                List.of(new ValidadorListaDeFuncionarios(),
                        new ValidadorDeEntidadeFuncionario(),
                        new ValidadorNomeFuncionarios(),
                        new ValidadorSalarioFuncionarios()));
    }

    public List<String> gerarListaDeNomeESalarioProcessadoFuncionario(Map<String, BigDecimal> nomeFuncionarioESalarioProcessadoMapa) {
        return nomeFuncionarioESalarioProcessadoMapa
                .entrySet()
                .stream()
                .map(mapa -> mapa.getKey().toUpperCase()
                        + " = "
                        + mapa.getValue())
                .collect(Collectors.toList());
    }
}