package br.com.jorgerabellodev.eniacproject.api.services.validations.validators;

import br.com.jorgerabellodev.eniacproject.api.errors.details.FieldMessage;
import br.com.jorgerabellodev.eniacproject.api.models.dto.CustomerDTO;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Customer;
import br.com.jorgerabellodev.eniacproject.api.repositories.CustomerRepository;
import br.com.jorgerabellodev.eniacproject.api.services.validations.annotations.CustomerUpdate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> {

    private final CustomerRepository repository;

    @Autowired
    public CustomerUpdateValidator(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(CustomerUpdate constraintAnnotation) {

    }

    @Override
    public boolean isValid(CustomerDTO updateCustomerDTO, ConstraintValidatorContext context) {
        List<FieldMessage> fieldMessages = new ArrayList<>();

        Customer customer = repository.findByEmail(updateCustomerDTO.getEmail());

        if (customer != null && !customer.getId().equals(updateCustomerDTO.getId())) {
            fieldMessages.add(new FieldMessage("email", "Emails already registered"));
        }

        for (FieldMessage fieldMessage : fieldMessages) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
                    .addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
        }
        return fieldMessages.isEmpty();
    }
}
