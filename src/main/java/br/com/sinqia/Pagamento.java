package br.com.sinqia;

import br.com.sinqia.cargos.*;
import br.com.sinqia.exceptions.*;
import br.com.sinqia.repositories.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

        FolhaDePagamento folhaDePagamento = pagamento.gerarFolhaDePagamento(aliquotas);

        Map<String, BigDecimal> nomeFuncionarioESalarioProcessadoMapa = pagamento.processarSalarioFuncionario(funcionarios, folhaDePagamento);

        pagamento.imprimirNomeESalarioProcessadoFuncionario(nomeFuncionarioESalarioProcessadoMapa);
    }


    public <T> void gerarDados(GeradorDeDados<T> geradorDeDados) {
        if (geradorDeDados == null) {
            throw new GeradorDeDadosNotFoundException("Gerador de dados não encontrado");
        }
        geradorDeDados.gerarDados();
    }

    public <T> List<T> findAll(Repository<T> repository) {
        if (repository == null) {
            throw new RepositoryNotFoundException("Repositório não encontrado");
        }
        return repository.findAll();
    }
    public  FolhaDePagamento gerarFolhaDePagamento(List<Aliquota> aliquotas) {
        if (aliquotas == null || aliquotas.isEmpty()) {
            throw new AliquotasNullException("Alíquotas não encontradas");
        }
        return new FolhaDePagamento(aliquotas);
    }

    public Map<String, BigDecimal> processarSalarioFuncionario(List<Funcionario> funcionarios, FolhaDePagamento folhaDePagamento) {
        if (funcionarios == null || funcionarios.isEmpty()) {
            throw new FuncionariosNullException("Funcionários não encontrados");
        }

        if (folhaDePagamento == null) {
            throw new FolhaDePagamentoNullException("Folha de pagamento não gerada");
        }

        if (funcionarios.stream().anyMatch(funcionario ->
                funcionario == null || funcionario.getNome() == null || funcionario.getNome().isBlank())) {
            throw new FuncionarioNotFoundException();
            //TODO testes para funcionário = null, nome do funcionário = null, nome do funcionário = vazio
            //TODO passar todas as mensagens para dentro das exceptions
        }

        return funcionarios
                .stream()
                .collect(Collectors.toMap(
                        Funcionario::getNome,
                        folhaDePagamento::processarSalario
                ));
    }

    public  void imprimirNomeESalarioProcessadoFuncionario( Map<String, BigDecimal> nomeFuncionarioESalarioProcessadoMapa) {
        nomeFuncionarioESalarioProcessadoMapa.forEach((key, value) ->
                System.out.println(
                key.toUpperCase()
                + " = "
                + value));
        //TODO formatar a exibição do valor
    }
}