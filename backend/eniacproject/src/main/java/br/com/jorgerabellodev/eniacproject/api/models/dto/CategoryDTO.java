package br.com.jorgerabellodev.eniacproject.api.models.dto;

import br.com.jorgerabellodev.eniacproject.api.models.entities.Category;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class CategoryDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "Required field")
    @Length(min = 3, max = 120, message = "The length must be between 3 and 120 characters")
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
