package nl.miwnn.se14.eatwell.repositories;

import nl.miwnn.se14.eatwell.model.Category;
import nl.miwnn.se14.eatwell.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Furkan Altay
 * Purpose for the class
 */

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByIngredientName (String ingredientName);
}
