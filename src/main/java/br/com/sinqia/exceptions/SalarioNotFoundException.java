package br.com.sinqia.exceptions;

public class SalarioNotFoundException extends RuntimeException{
    public SalarioNotFoundException() {
        super("Salário não encontrado");
    }
}
