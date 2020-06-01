package br.com.jorgerabellodev.eniacproject.api.services.validations.validators;

import br.com.jorgerabellodev.eniacproject.api.errors.details.FieldMessage;
import br.com.jorgerabellodev.eniacproject.api.models.dto.NewCustomerDTO;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Customer;
import br.com.jorgerabellodev.eniacproject.api.models.entities.enums.CustomerType;
import br.com.jorgerabellodev.eniacproject.api.repositories.CustomerRepository;
import br.com.jorgerabellodev.eniacproject.api.services.validations.annotations.CustomerInsert;
import br.com.jorgerabellodev.eniacproject.api.services.validations.utils.CPFCNPJValidator;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, NewCustomerDTO> {

    private final CustomerRepository repository;

    @Autowired
    public CustomerInsertValidator(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(CustomerInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(NewCustomerDTO newCustomerDTO, ConstraintValidatorContext context) {
        List<FieldMessage> fieldMessages = new ArrayList<>();

        if (newCustomerDTO.getCustomerType().equals(CustomerType.INDIVIDUAL.getCode()) && !CPFCNPJValidator
                .isValidCPF(newCustomerDTO.getCpfCnpj())) {
            fieldMessages.add(new FieldMessage("cpfCnpj", "Invalid CPF"));
        }

        if (newCustomerDTO.getCustomerType().equals(CustomerType.LEGAL.getCode()) && !CPFCNPJValidator
                .isValidCNPJ(newCustomerDTO.getCpfCnpj())) {
            fieldMessages.add(new FieldMessage("cpfCnpj", "Invalid CNPJ"));
        }

        Customer customer = repository.findByEmail(newCustomerDTO.getEmail());

        if (customer != null) {
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
