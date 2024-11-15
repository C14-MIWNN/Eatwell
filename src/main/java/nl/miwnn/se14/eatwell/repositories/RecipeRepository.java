package nl.miwnn.se14.eatwell.repositories;

import nl.miwnn.se14.eatwell.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RecipeRepository extends JpaRepository <Recipe, Long> {
    Optional<Recipe> findByName(String name);
}
