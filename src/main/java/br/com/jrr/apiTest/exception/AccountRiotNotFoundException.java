package br.com.jrr.apiTest.exception;

// Crie esta classe em algum pacote de exceções, como por exemplo, no pacote 'exceptions'
public class AccountRiotNotFoundException extends RuntimeException {
    public AccountRiotNotFoundException(String message) {
        super(message);
    }
}
