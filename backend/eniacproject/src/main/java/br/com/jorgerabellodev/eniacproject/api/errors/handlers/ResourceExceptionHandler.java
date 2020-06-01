package br.com.jorgerabellodev.eniacproject.api.errors.handlers;

import static java.lang.System.currentTimeMillis;

import br.com.jorgerabellodev.eniacproject.api.errors.details.ErrorDetails;
import br.com.jorgerabellodev.eniacproject.api.errors.details.ValidationError;
import br.com.jorgerabellodev.eniacproject.api.errors.exceptions.DataIntegrityException;
import br.com.jorgerabellodev.eniacproject.api.errors.exceptions.RequiredCustomerException;
import br.com.jorgerabellodev.eniacproject.api.errors.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                currentTimeMillis()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RequiredCustomerException.class)
    public ResponseEntity<?> requiredCustomerExceptionHandler(RequiredCustomerException e) {
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                currentTimeMillis()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<?> dataIntegrityExceptionHandler(DataIntegrityException e) {
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                currentTimeMillis()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNoValidExceptionHandler(MethodArgumentNotValidException e) {

        ValidationError validationError = new ValidationError(
                HttpStatus.BAD_REQUEST.value(),
                "Validation error",
                currentTimeMillis()
        );

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }
}
