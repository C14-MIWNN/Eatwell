package nl.miwnn.se14.eatwell.controller;

import nl.miwnn.se14.eatwell.model.Category;
import nl.miwnn.se14.eatwell.model.EatWellUser;
<<<<<<< HEAD
import nl.miwnn.se14.eatwell.model.Recipe;
import nl.miwnn.se14.eatwell.repositories.CategoryRepository;
=======
>>>>>>> cfbf753eb87f8c6167d7602a82d162f792b187c8
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

    public InitializeController(RecipeRepository recipeRepository,
                                EatWellUserService eatWellUserService, CategoryRepository categoryRepository){
        this.eatWellUserService = eatWellUserService;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }

    @EventListener
    private void seed(ContextRefreshedEvent ignoredEvent){
        if (recipeRepository.findAll().isEmpty()) {
            initializeDB();
        }
    }

    private void initializeDB(){
<<<<<<< HEAD
=======

>>>>>>> cfbf753eb87f8c6167d7602a82d162f792b187c8
    }


    private void makeEatWellUser(String username, String password){
        EatWellUser user = new EatWellUser();
        user.setUsername(username);
        user.setPassword(password);
        eatWellUserService.save(user);
    }
<<<<<<< HEAD

    private void  makeCategory(String name){
        Category category = new Category();
        category.setCategory_name(name);
        categoryRepository.save(category);
    }
=======
>>>>>>> cfbf753eb87f8c6167d7602a82d162f792b187c8
}
