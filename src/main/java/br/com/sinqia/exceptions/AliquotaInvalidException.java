package br.com.sinqia.exceptions;

public class AliquotaInvalidException extends RuntimeException{
    public AliquotaInvalidException() {
        super("Alíquota inválida");
    }
}
