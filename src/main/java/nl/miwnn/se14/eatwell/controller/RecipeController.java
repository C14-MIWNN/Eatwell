package nl.miwnn.se14.eatwell.controller;

import nl.miwnn.se14.eatwell.dto.EatWellUserDTO;
import nl.miwnn.se14.eatwell.model.EatWellUser;
import nl.miwnn.se14.eatwell.model.Ingredient;
import nl.miwnn.se14.eatwell.model.Recipe;
import nl.miwnn.se14.eatwell.repositories.CategoryRepository;
import nl.miwnn.se14.eatwell.repositories.EatWellUserRepository;
import nl.miwnn.se14.eatwell.repositories.IngredientRepository;
import nl.miwnn.se14.eatwell.repositories.RecipeRepository;
import nl.miwnn.se14.eatwell.service.EatWellUserService;
import nl.miwnn.se14.eatwell.service.mapper.EatWellUserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Furkan Altay
 * Purpose for the class
 */

@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;
    private final EatWellUserRepository eatWellUserRepository;


    public RecipeController(RecipeRepository recipeRepository,
                            CategoryRepository categoryRepository,
                            IngredientRepository ingredientRepository, EatWellUserRepository eatWellUserRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.eatWellUserRepository = eatWellUserRepository;
    }

    @GetMapping({"/recipe/overview"})
    private String showRecipeOverview(Model datamodel) {
        datamodel.addAttribute("searchForm", new Recipe());
        datamodel.addAttribute("allRecipes", recipeRepository.findAll());
        return "recipeOverview";
    }

    @GetMapping({"/recipe/new"})
    private String showRecipeCreation(Model datamodel) {
        datamodel.addAttribute("searchForm", new Recipe());
        datamodel.addAttribute("newRecipe", new Recipe());
        datamodel.addAttribute("allCategories", categoryRepository.findAll());
        datamodel.addAttribute("allIngredients", ingredientRepository.findAll());
        datamodel.addAttribute("formIngredient", new Ingredient());
        datamodel.addAttribute("formModalHidden", true);
        return "recipeCreation";
    }

    @PostMapping({"/recipe/add"})
    private String saveOrUpdateRecipe(@ModelAttribute("newRecipe") Recipe recipe,
                                      BindingResult result) {
        Optional<Recipe> sameName = recipeRepository.findByName(recipe.getRecipe_name());
        if (sameName.isPresent() && !sameName.get().getRecipe_id().equals(recipe.getRecipe_id())) {
            result.addError(new FieldError("newRecipe",
                    "recipe_name",
                    "This recipe already exists!"));
        }
        if (result.hasErrors()) {
            return "recipeCreation";
        }
        EatWellUser currentUser = (EatWellUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recipe.setAuthor(currentUser);

        recipeRepository.save(recipe);
        return "redirect:/recipe/new";
    }


    @GetMapping("/surpriseMe")
    public String showSurprise(Model datamodel) {
        List<Recipe> Recipes = recipeRepository.findAll();
        if (Recipes.isEmpty()) {
            datamodel.addAttribute("error", "No recipes");
            return "surpriseMe";
        }

        Recipe randomRecipe = Recipes.get((int)(Math.random() * Recipes.size()));
        datamodel.addAttribute("randomRecipe", randomRecipe);

        return "surpriseMe";
    }


    @GetMapping({"/recipe/{recipeName}"})
    private String showRecipeDetails(@PathVariable("recipeName") String recipeName, Model datamodel)  {
        Optional<Recipe> recipeOptional = recipeRepository.findByName(recipeName);

        if(recipeOptional.isEmpty()) {
            return "recipeOverview";
        }
        datamodel.addAttribute("recipe", recipeOptional.get());
        return "recipeDetails";
    }


    @GetMapping("/search")
    private String showRecipeOverviewNew(Model datamodel) {
        datamodel.addAttribute("searchForm", new Recipe());
        return "recipeSearch";
    }


    @PostMapping("/search")
    private String showRecipesBySearchTerm(
            @ModelAttribute("searchForm") Recipe recipe,
            BindingResult result,
            Model datamodel) {

        if (recipe.getRecipe_name().isEmpty()) {
            datamodel.addAttribute("searchForm", new Recipe());
            datamodel.addAttribute("allRecipes", recipeRepository.findAll());
            return "recipeSearch";
        }

        Optional<List<Recipe>> searchResults = recipeRepository.findByNameContaining(recipe.getRecipe_name());

        if (searchResults.isEmpty() || searchResults.get().isEmpty()) {
            datamodel.addAttribute("errorMessage",
                    "No recipes found. Try searching with a different term!");
        }

        if (result.hasErrors()) {
            return "homepage";
        }

        datamodel.addAttribute("searchForm", recipe);
        datamodel.addAttribute("allRecipes", searchResults.get());
        return "recipeSearch";
    }

}
