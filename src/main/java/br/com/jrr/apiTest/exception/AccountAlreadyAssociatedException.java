package br.com.jrr.apiTest.exception;

public class AccountAlreadyAssociatedException extends RuntimeException {
    public AccountAlreadyAssociatedException(String message) {
        super(message);
    }
}
