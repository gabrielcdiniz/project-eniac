package br.com.jorgerabellodev.eniacproject.api.models.dto;

import java.io.Serializable;

public class NewCategoryDTO implements Serializable {

    private String name;

    public NewCategoryDTO() {
    }

    public NewCategoryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
