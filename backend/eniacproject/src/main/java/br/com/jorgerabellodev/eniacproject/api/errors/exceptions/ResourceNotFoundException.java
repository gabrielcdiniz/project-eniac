package br.com.jorgerabellodev.eniacproject.api.errors.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
