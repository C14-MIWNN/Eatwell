package nl.miwnn.se14.eatwell.controller;

import nl.miwnn.se14.eatwell.dto.EatWellUserDTO;
import nl.miwnn.se14.eatwell.model.Ingredient;
import nl.miwnn.se14.eatwell.model.Recipe;
import nl.miwnn.se14.eatwell.repositories.CategoryRepository;
import nl.miwnn.se14.eatwell.repositories.IngredientRepository;
import nl.miwnn.se14.eatwell.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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


    public RecipeController(RecipeRepository recipeRepository,
                            CategoryRepository categoryRepository,
                            IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping({"/","/recipe/overview"})
    private String showRecipeOverview(Model datamodel) {
        datamodel.addAttribute("allRecipes", recipeRepository.findAll());
        datamodel.addAttribute("formUser", new EatWellUserDTO());
        datamodel.addAttribute("formModalHidden", true);
        return "recipeOverview";
    }

    @GetMapping({"/recipe/new"})
    private String showRecipeCreation(Model datamodel) {
        datamodel.addAttribute("newRecipe", new Recipe());
        datamodel.addAttribute("allCategories", categoryRepository.findAll());
        datamodel.addAttribute("formIngredient", new Ingredient());
        datamodel.addAttribute("formModalHidden", true);
        return "recipeCreation";
    }

    @PostMapping({"/recipe/new"})
    private String saveOrUpdateRecipe(@ModelAttribute("newRecipe") Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "recipeCreation";
        }

        recipeRepository.save(recipe);
        return "redirect:/recipe/new";
    }

    @GetMapping("/surpriseMe")
    public String showSurprise(Model datamodel) {
        Recipe randomRecipe = recipeRepository.findAll()
                .stream()
                .findAny()
                .orElse(null);

        datamodel.addAttribute("r", randomRecipe);
        datamodel.addAttribute("fields", randomRecipe.getIngredients());
        return "surpriseMe";
    }


    @GetMapping({"/recipe/{recipeName}"})
    private String showRecipeDetails(@PathVariable("recipeName") String recipeNaam, Model datamodel)  {
        Optional<Recipe> recipeOptional = recipeRepository.findByName(recipeNaam);

        if(recipeOptional.isEmpty()) {
            return "recipeOverview";
        }
        datamodel.addAttribute("recipe", recipeOptional.get());
        return "recipeDetails";
    }

    @PostMapping("/ingredient/add")
    private String saveOrUpdateIngredient(@ModelAttribute("formIngredient") Ingredient ingredient,
                                          BindingResult result, Model datamodel) {
        Optional<Ingredient> sameName = ingredientRepository.findByIngredientName(ingredient.getIngredientName());
        if (sameName.isPresent() && !sameName.get().getIngredient_id().equals(ingredient.getIngredient_id())) {
            result.addError(new FieldError("formIngredient",
                    "name",
                    "this ingredient has already been added"));
        }
        if (result.hasErrors()){
            datamodel.addAttribute("formIngredient", new Ingredient());
            datamodel.addAttribute("formModalHidden", false);
            return "recipeCreation";
        }

        ingredientRepository.save(ingredient);
        return "recipeCreation";
    }

}