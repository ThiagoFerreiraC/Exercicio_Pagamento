package br.com.sinqia.exceptions;

public class FuncionarioNotFoundException extends RuntimeException{
    public FuncionarioNotFoundException(String message) {
        super(message);
    }
}
