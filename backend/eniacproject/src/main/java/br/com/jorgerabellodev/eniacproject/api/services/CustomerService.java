package br.com.jorgerabellodev.eniacproject.api.services;

import br.com.jorgerabellodev.eniacproject.api.errors.exceptions.DataIntegrityException;
import br.com.jorgerabellodev.eniacproject.api.errors.exceptions.ResourceNotFoundException;
import br.com.jorgerabellodev.eniacproject.api.models.dto.CustomerDTO;
import br.com.jorgerabellodev.eniacproject.api.models.dto.NewCustomerDTO;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Address;
import br.com.jorgerabellodev.eniacproject.api.models.entities.City;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Customer;
import br.com.jorgerabellodev.eniacproject.api.models.entities.enums.CustomerType;
import br.com.jorgerabellodev.eniacproject.api.repositories.CustomerRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final AddressService addressService;

    @Autowired
    public CustomerService(CustomerRepository repository, AddressService addressService) {
        this.repository = repository;
        this.addressService = addressService;
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(UUID uuid) {
        Optional<Customer> customer = repository.findById(uuid);
        return customer.orElseThrow(() -> new ResourceNotFoundException(
                "Resource not found ! Id: " + uuid + ", Type: " + Customer.class.getName()));
    }

    @Transactional
    public Customer save(Customer customer) {
        customer = repository.save(customer);
        addressService.saveAll(customer.getAdresses());
        return customer;
    }

    public Customer update(Customer customer) {
        Customer updatedCustomer = findById(customer.getId());
        updateData(updatedCustomer, customer);
        return repository.save(updatedCustomer);
    }

    public void delete(UUID id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("You cannot delete a customer with related data");
        }
    }

    public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    private void updateData(Customer updatedCustomer, Customer customer) {
        updatedCustomer.setName(customer.getName());
        updatedCustomer.setEmail(customer.getEmail());
    }

    public Customer fromDTO(CustomerDTO customerDTO) {
        return new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getEmail(),
                null,
                null
        );
    }

    public Customer fromDTO(NewCustomerDTO newCustomerDTO) {
        Customer customer = new Customer(
                null,
                newCustomerDTO.getName(),
                newCustomerDTO.getEmail(),
                newCustomerDTO.getCpfCnpj(),
                CustomerType.toEnum(newCustomerDTO.getCustomerType())
        );

        City city = new City(newCustomerDTO.getCityId(), null, null);

        Address address = new Address(
                null,
                newCustomerDTO.getStreet(),
                newCustomerDTO.getLineOne(),
                newCustomerDTO.getLineTwo(),
                newCustomerDTO.getNeighborhood(),
                newCustomerDTO.getPostalCode(),
                customer,
                city
        );

        customer.getAdresses().add(address);
        customer.getPhones().add(newCustomerDTO.getPhoneOne());

        if (newCustomerDTO.getPhoneTwo() != null) {
            customer.getPhones().add(newCustomerDTO.getPhoneTwo());
        }

        if (newCustomerDTO.getPhoneThree() != null) {
            customer.getPhones().add(newCustomerDTO.getPhoneThree());
        }
        return customer;
    }
}
