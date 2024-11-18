package nl.miwnn.se14.eatwell.controller;

import nl.miwnn.se14.eatwell.model.EatWellUser;
import nl.miwnn.se14.eatwell.model.Recipe;
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

    public InitializeController(RecipeRepository recipeRepository,
                                EatWellUserService eatWellUserService){
        this.eatWellUserService = eatWellUserService;
        this.recipeRepository = recipeRepository;
    }

    @EventListener
    private void seed(ContextRefreshedEvent ignoredEvent){
        if (recipeRepository.findAll().isEmpty()) {
            initializeDB();
        }
    }

    private void initializeDB(){
    makeRecipeCategory("Breakfast", "Text");
    makeRecipeCategory("Vegan", "Text");
    makeRecipeCategory("Meats", "Text");
    makeRecipeCategory("Desserts", "Text");
    makeRecipeCategory("Appetizers", "Text");

    }


    private void makeEatWellUser(String username, String password){
        EatWellUser user = new EatWellUser();
        user.setUsername(username);
        user.setPassword(password);
        eatWellUserService.save(user);
    }

    private void  makeRecipeCategory(String name, String description){
        Recipe recipe = new Recipe();
        recipe.setRecipe_name(name);
        recipe.setDescription(description);
        recipeRepository.save(recipe);

    }
}
