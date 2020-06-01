package br.com.jorgerabellodev.eniacproject.api.endpoints;

import br.com.jorgerabellodev.eniacproject.api.models.dto.CategoryDTO;
import br.com.jorgerabellodev.eniacproject.api.models.dto.NewCategoryDTO;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Category;
import br.com.jorgerabellodev.eniacproject.api.services.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.List;
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
@RequestMapping(value = "/categories", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryEndpoint {

    private final CategoryService service;

    @Autowired
    public CategoryEndpoint(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(
            value = "Retrieve all registered categories",
            notes = "Retrieve all registered categories",
            response = CategoryDTO.class,
            responseContainer = "List",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All registered categories"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> findAll(
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {
        List<Category> all = service.findAll();
        List<CategoryDTO> allCategories = all.stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(
            value = "Retrieve a category based on a id",
            notes = "Retrieve a category based on a id",
            response = Category.class,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The category register"),
            @ApiResponse(code = 404, message = "Category not found"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> findById(
            @PathVariable("id") Long id,
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {
        Category category = service.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping(value = "/page")
    @ApiOperation(
            value = "Retrieve all registered categories",
            notes = "Retrieve all registered categories",
            response = CategoryDTO.class,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The category register"),
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
        Page<Category> all = service.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoryDTO> allCategories = all.map(CategoryDTO::new);
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(
            value = "Add a new category",
            notes = "Return a URI resource for the created category",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created a new category"),
            @ApiResponse(code = 400, message = "Field validation error"),
            @ApiResponse(code = 400, message = "This category is already registered"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> save(
            @Valid @RequestBody NewCategoryDTO newCategoryDTO,
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {
        Category category = service.fromDTO(newCategoryDTO);
        Category savedCategory = service.save(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCategory.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    @ApiOperation(
            value = "Update a category data based on a id",
            notes = "Update a category data based on a id",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category successfully updated"),
            @ApiResponse(code = 404, message = "Category not found"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> update(
            @Valid @RequestBody CategoryDTO categoryDTO,
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {
        Category category = service.fromDTO(categoryDTO);
        Category updatedCategory = service.update(category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Delete a category based on a id",
            notes = "Delete a category based on a id",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Category successfully deleted"),
            @ApiResponse(code = 404, message = "Category not found"),
            @ApiResponse(code = 415, message = "Unsuported media type, change your Content-Type header to application/json")
    })
    public ResponseEntity<?> delete(
            @PathVariable("id") Long id,
            @RequestHeader(
                    value = "Content-Type",
                    required = true,
                    name = "Content-Type",
                    defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
