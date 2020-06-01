package br.com.jorgerabellodev.eniacproject.api.models.dto;

import br.com.jorgerabellodev.eniacproject.api.models.entities.Customer;
import br.com.jorgerabellodev.eniacproject.api.services.validations.annotations.CustomerUpdate;
import java.io.Serializable;
import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

@CustomerUpdate
public class CustomerDTO implements Serializable {

    private UUID id;

    @NotEmpty(message = "Required field")
    @Length(min = 3, max = 120, message = "The length must be between 3 and 120 characters")
    private String name;

    @NotEmpty(message = "Required field")
    @Email(message = "Please type a valid e-mail address")
    @Length(min = 10, max = 120, message = "The length must be between 10 and 120 characters")
    private String email;

    public CustomerDTO() {
    }

    public CustomerDTO(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
