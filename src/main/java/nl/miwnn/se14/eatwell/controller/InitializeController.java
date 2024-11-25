package nl.miwnn.se14.eatwell.controller;

import nl.miwnn.se14.eatwell.model.Category;
import nl.miwnn.se14.eatwell.model.EatWellUser;
import nl.miwnn.se14.eatwell.model.Ingredient;
import nl.miwnn.se14.eatwell.model.Recipe;
import nl.miwnn.se14.eatwell.repositories.CategoryRepository;

import nl.miwnn.se14.eatwell.repositories.IngredientRepository;
import nl.miwnn.se14.eatwell.repositories.RecipeRepository;
import nl.miwnn.se14.eatwell.service.EatWellUserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

/**
 * @author Bart Molenaars
 * Sets initial data for testing purposes.
 */

@Controller
public class InitializeController {
    private final EatWellUserService eatWellUserService;
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;

    public InitializeController(RecipeRepository recipeRepository,
                                EatWellUserService eatWellUserService, CategoryRepository categoryRepository, IngredientRepository ingredientRepository){
        this.eatWellUserService = eatWellUserService;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @EventListener
    private void seed(ContextRefreshedEvent ignoredEvent){
        if (recipeRepository.findAll().isEmpty()) {
            initializeDB();
        }
    }

    private void initializeDB(){
    makeEatWellUser("furkan", "123456");

    makeCategory("Breakfast");
    makeCategory("Desserts");
    makeCategory("Meats");
    makeCategory("Vegan");
    makeCategory("Appetizers");

    makeIngredient("Rice");
    makeIngredient("Butter");
    makeIngredient("Cheese");
    makeIngredient("Sugar");
    makeIngredient("Pepper");
    makeIngredient("Salt");
    makeIngredient("Honey");
    makeIngredient("Ginger");
    makeIngredient("Chili Pepper");
    makeIngredient("Mustard");

    }


    private void makeEatWellUser(String username, String password){
        EatWellUser user = new EatWellUser();
        user.setUsername(username);
        user.setPassword(password);
        eatWellUserService.save(user);
    }

    private void  makeCategory(String name){
        Category category = new Category();
        category.setCategory_name(name);
        categoryRepository.save(category);
    }

    private void  makeRecipe(String name, String description){
        Recipe recipe = new Recipe();
        recipe.setRecipe_name(name);
        recipe.setDescription(description);
        recipeRepository.save(recipe);
    }

    private void  makeIngredient(String name){
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName(name);
        ingredientRepository.save(ingredient);
    }
}
