package br.com.jorgerabellodev.eniacproject.api.services;

import br.com.jorgerabellodev.eniacproject.api.errors.exceptions.ResourceNotFoundException;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Category;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Product;
import br.com.jorgerabellodev.eniacproject.api.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> product = repository.findById(id);
        return product.orElseThrow(() -> new ResourceNotFoundException(
                "Resource not found ! Id: " + id + ", Type: " + Product.class.getName()));
    }

    public Page<Product> search(
            String name,
            List<Long> ids,
            Integer page,
            Integer linesPerPage,
            String orderBy,
            String direction) {

        PageRequest pageRequest = PageRequest.of(
                page,
                linesPerPage,
                Direction.valueOf(direction),
                orderBy);
        List<Category> categories = categoryService.findAllById(ids);
        return repository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
    }
}
