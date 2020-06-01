package br.com.jorgerabellodev.eniacproject.api.models.dto;

import br.com.jorgerabellodev.eniacproject.api.services.validations.annotations.CustomerInsert;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

@CustomerInsert
public class NewCustomerDTO implements Serializable {

    @NotEmpty(message = "Required field")
    @Length(min = 3, max = 120, message = "The length must be between 3 and 120 characters")
    private String name;

    @NotEmpty(message = "Required field")
    @Email(message = "Please type a valid e-mail address")
    @Length(min = 10, max = 120, message = "The length must be between 10 and 120 characters")
    private String email;

    @NotEmpty(message = "Required field")
    private String cpfCnpj;

    private Integer customerType;

    @NotEmpty(message = "Required field")
    private String street;

    private String lineOne;

    private String lineTwo;

    @NotEmpty(message = "Required field")
    private String neighborhood;

    @NotEmpty(message = "Required field")
    private String postalCode;

    @NotEmpty(message = "Required field")
    private String phoneOne;

    private String phoneTwo;

    private String phoneThree;

    private Long cityId;

    public NewCustomerDTO() {
    }

    public NewCustomerDTO(String name, String email, String cpfCnpj, Integer customerType, String street,
            String lineOne, String lineTwo, String neighborhood, String postalCode, String phoneOne,
            String phoneTwo, String phoneThree, Long cityId) {
        this.name = name;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
        this.customerType = customerType;
        this.street = street;
        this.lineOne = lineOne;
        this.lineTwo = lineTwo;
        this.neighborhood = neighborhood;
        this.postalCode = postalCode;
        this.phoneOne = phoneOne;
        this.phoneTwo = phoneTwo;
        this.phoneThree = phoneThree;
        this.cityId = cityId;
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

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLineOne() {
        return lineOne;
    }

    public void setLineOne(String lineOne) {
        this.lineOne = lineOne;
    }

    public String getLineTwo() {
        return lineTwo;
    }

    public void setLineTwo(String lineTwo) {
        this.lineTwo = lineTwo;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneOne() {
        return phoneOne;
    }

    public void setPhoneOne(String phoneOne) {
        this.phoneOne = phoneOne;
    }

    public String getPhoneTwo() {
        return phoneTwo;
    }

    public void setPhoneTwo(String phoneTwo) {
        this.phoneTwo = phoneTwo;
    }

    public String getPhoneThree() {
        return phoneThree;
    }

    public void setPhoneThree(String phoneThree) {
        this.phoneThree = phoneThree;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
