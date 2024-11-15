package nl.miwnn.se14.eatwell.repositories;

import nl.miwnn.se14.eatwell.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Furkan Altay
 * Purpose for the class
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName (String name);
}
