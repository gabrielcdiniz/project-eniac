package br.com.jorgerabellodev.eniacproject.api.errors.details;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends ErrorDetails {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String message, Long timeStamp) {
        super(status, message, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
