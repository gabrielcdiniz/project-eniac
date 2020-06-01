package br.com.jorgerabellodev.eniacproject.api.endpoints;

import br.com.jorgerabellodev.eniacproject.api.models.entities.Order;
import br.com.jorgerabellodev.eniacproject.api.services.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderEndpoint {

    private final OrderService service;

    @Autowired
    public OrderEndpoint(OrderService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(
            value = "Retrieve all registered orders",
            notes = "Retrieve all registered orders",
            response = Order.class,
            responseContainer = "List",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All registered orders"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> findAll(
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {
        List<Order> all = service.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(
            value = "Retrieve a order based on a id",
            notes = "Retrieve a order based on a id",
            response = Order.class,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The order register"),
            @ApiResponse(code = 404, message = "Order not found"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> findById(
            @PathVariable("id") Long id,
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {
        Order order = service.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(
            value = "Add a new order",
            notes = "Return a URI resource for the created order",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created a new order"),
            @ApiResponse(code = 400, message = "Field validation error"),
            @ApiResponse(code = 400, message = "This order is already registered"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> save(
            @Valid @RequestBody Order order,
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {
        Order savedOrder = service.save(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrder.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
