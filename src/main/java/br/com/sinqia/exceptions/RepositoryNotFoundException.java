package br.com.sinqia.exceptions;

public class RepositoryNotFoundException extends RuntimeException{
    public RepositoryNotFoundException(String message) {
        super(message);
    }
}
