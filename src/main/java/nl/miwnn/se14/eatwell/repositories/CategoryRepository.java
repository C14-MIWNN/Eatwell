package nl.miwnn.se14.eatwell.repositories;

import nl.miwnn.se14.eatwell.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName (String categoryName);
}
