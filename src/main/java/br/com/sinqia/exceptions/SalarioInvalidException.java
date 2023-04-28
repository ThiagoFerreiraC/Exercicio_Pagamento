package br.com.sinqia.exceptions;

public class SalarioInvalidException extends RuntimeException{
    public SalarioInvalidException() {
        super("Salário inválido");
    }
}
