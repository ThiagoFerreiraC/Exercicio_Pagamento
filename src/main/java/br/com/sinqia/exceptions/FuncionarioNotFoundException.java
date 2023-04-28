package br.com.sinqia.exceptions;

public class FuncionarioNotFoundException extends RuntimeException{
    public FuncionarioNotFoundException() {
        super("Funcionario não encontrado");
    }
}
