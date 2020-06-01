package br.com.jorgerabellodev.eniacproject.api.errors.exceptions;

public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException(String message) {
        super(message);
    }
}
