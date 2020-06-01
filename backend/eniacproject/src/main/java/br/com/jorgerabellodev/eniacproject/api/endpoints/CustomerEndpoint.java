package br.com.jorgerabellodev.eniacproject.api.endpoints;

import br.com.jorgerabellodev.eniacproject.api.models.dto.CustomerDTO;
import br.com.jorgerabellodev.eniacproject.api.models.dto.NewCustomerDTO;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Customer;
import br.com.jorgerabellodev.eniacproject.api.services.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerEndpoint {

    private final CustomerService service;

    @Autowired
    public CustomerEndpoint(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(
            value = "Retrieve all registered customers",
            notes = "Retrieve all registered customers",
            response = CustomerDTO.class,
            responseContainer = "List",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All registered customers"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> findAll(
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {
        List<Customer> all = service.findAll();
        List<CustomerDTO> allCustomers = all.stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(
            value = "Retrieve a customer based on a uuid",
            notes = "Retrieve a customer based on a uuid",
            response = Customer.class,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The customer register"),
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> findById(
            @PathVariable("id") UUID id,
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {
        Customer customer = service.findById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping(value = "/page")
    @ApiOperation(
            value = "Retrieve all registered customers",
            notes = "Retrieve all registered customers",
            response = CustomerDTO.class,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The customer register"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> findAllPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {
        Page<Customer> all = service.findPage(page, linesPerPage, orderBy, direction);
        Page<CustomerDTO> allCustomers = all.map(CustomerDTO::new);
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(
            value = "Add a new customer",
            notes = "Return a URI resource for the created customer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created a new customer"),
            @ApiResponse(code = 400, message = "Field validation error"),
            @ApiResponse(code = 400, message = "This customer is already registered"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> save(
            @Valid @RequestBody NewCustomerDTO newCustomerDTO,
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {
        Customer customer = service.fromDTO(newCustomerDTO);
        Customer savedCustomer = service.save(customer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCustomer.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    @ApiOperation(
            value = "Update a customer data based on a uuid",
            notes = "Update a customer data based on a uuid",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer successfully updated"),
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> update(
            @Valid @RequestBody CustomerDTO customerDTO,
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {
        Customer customer = service.fromDTO(customerDTO);
        Customer updatedCustomer = service.update(customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Delete a customer based on a uuid",
            notes = "Delete a customer based on a uuid",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Customer successfully deleted"),
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> delete(
            @PathVariable("id") UUID id,
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
