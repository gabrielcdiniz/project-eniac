package br.com.jorgerabellodev.eniacproject.api.services;

import br.com.jorgerabellodev.eniacproject.api.errors.exceptions.DataIntegrityException;
import br.com.jorgerabellodev.eniacproject.api.errors.exceptions.ResourceNotFoundException;
import br.com.jorgerabellodev.eniacproject.api.models.dto.CategoryDTO;
import br.com.jorgerabellodev.eniacproject.api.models.dto.NewCategoryDTO;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Category;
import br.com.jorgerabellodev.eniacproject.api.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> category = repository.findById(id);
        return category.orElseThrow(() -> new ResourceNotFoundException(
                "Resource not found ! Id: " + id + ", Type: " + Category.class.getName()));
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public Category update(Category category) {
        Category updatedCategory = findById(category.getId());
        updateData(updatedCategory, category);
        return repository.save(updatedCategory);
    }

    public void delete(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("You cannot delete a category associated with products");
        }
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Category fromDTO(NewCategoryDTO newCategoryDTO) {
        return new Category(null, newCategoryDTO.getName());
    }

    public Category fromDTO(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }

    private void updateData(Category updatedCategory, Category category) {
        updatedCategory.setName(category.getName());
    }

    public List<Category> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }
}
