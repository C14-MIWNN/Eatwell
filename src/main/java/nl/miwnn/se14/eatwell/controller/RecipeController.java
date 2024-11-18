package nl.miwnn.se14.eatwell.controller;

import nl.miwnn.se14.eatwell.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Furkan Altay
 * Purpose for the class
 */
@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;


    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping({"/","/recipe/overview"})
    private String showRecipeOverview(Model datamodel) {
        datamodel.addAttribute("allRecipes", recipeRepository.findAll());
        return "recipeOverview";
    }

}
