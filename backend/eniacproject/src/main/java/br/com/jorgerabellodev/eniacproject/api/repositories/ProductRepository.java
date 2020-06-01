package br.com.jorgerabellodev.eniacproject.api.repositories;

import br.com.jorgerabellodev.eniacproject.api.models.entities.Category;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional(readOnly = true)
    Page<Product> findDistinctByNameContainingAndCategoriesIn(
            String name,
            List<Category> categories,
            Pageable pageable
    );
}
