package br.com.jorgerabellodev.eniacproject.api.errors.exceptions;

public class RequiredCustomerException extends RuntimeException {

    public RequiredCustomerException(String message) {
        super(message);
    }
}
