package br.com.jorgerabellodev.eniacproject.api.models.dto;

import br.com.jorgerabellodev.eniacproject.api.models.entities.Product;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDTO implements Serializable {

    private Long id;
    private String name;
    private BigDecimal price;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
