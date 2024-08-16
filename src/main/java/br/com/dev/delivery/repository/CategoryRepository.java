package br.com.dev.delivery.repository;

import br.com.dev.delivery.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    //@Query("SELECT c FROM Category c JOIN FETCH c.products p WHERE c.id = :category_id")
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.products WHERE c.id = :category_id")
    Category findCategoriesWithProducts(@Param("category_id") Long category_id);

}
