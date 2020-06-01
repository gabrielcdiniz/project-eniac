package br.com.jorgerabellodev.eniacproject.api.endpoints;

import br.com.jorgerabellodev.eniacproject.api.endpoints.utils.URL;
import br.com.jorgerabellodev.eniacproject.api.models.dto.CategoryDTO;
import br.com.jorgerabellodev.eniacproject.api.models.dto.ProductDTO;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Category;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Product;
import br.com.jorgerabellodev.eniacproject.api.services.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductEndpoint {

    private final ProductService service;

    @Autowired
    public ProductEndpoint(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(
            value = "Retrieve a product based on a id",
            notes = "Retrieve a product based on a id",
            response = Category.class,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The product register"),
            @ApiResponse(code = 404, message = "Product not found"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> findById(
            @PathVariable("id") Long id,
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {
        Product product = service.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(
            value = "Retrieve all registered products",
            notes = "Retrieve all registered products",
            response = CategoryDTO.class,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The products register"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> findAllPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {

        List<Long> ids = URL.decodeLongList(categories);

        String decodedName = URL.decodeParam(name);

        Page<Product> all = service.search(decodedName, ids, page, linesPerPage, orderBy, direction);
        Page<ProductDTO> allProducts = all.map(ProductDTO::new);
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }
}
