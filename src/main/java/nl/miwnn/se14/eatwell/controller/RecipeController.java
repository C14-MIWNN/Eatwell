package nl.miwnn.se14.eatwell.controller;

import nl.miwnn.se14.eatwell.dto.EatWellUserDTO;
import nl.miwnn.se14.eatwell.dto.RecipeDTO;
import nl.miwnn.se14.eatwell.model.EatWellUser;
import nl.miwnn.se14.eatwell.model.Ingredient;
import nl.miwnn.se14.eatwell.model.Recipe;
import nl.miwnn.se14.eatwell.repositories.CategoryRepository;
import nl.miwnn.se14.eatwell.repositories.EatWellUserRepository;
import nl.miwnn.se14.eatwell.repositories.IngredientRepository;
import nl.miwnn.se14.eatwell.repositories.RecipeRepository;
import nl.miwnn.se14.eatwell.service.EatWellUserService;
import nl.miwnn.se14.eatwell.service.mapper.EatWellUserMapper;
import nl.miwnn.se14.eatwell.service.mapper.RecipeMapper;
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
        datamodel.addAttribute("formIngredient", new Ingredient());
        datamodel.addAttribute("formModalHidden", true);
        return "recipeCreation";
    }

//    @PostMapping("/recipe/new")
//    private String saveOrUpdateRecipe(@ModelAttribute("newRecipe") RecipeDTO recipeDTO,
//                                      BindingResult bindingResult,
//                                      Model datamodel){
//        Recipe newRecipe = RecipeMapper.fromDTO(recipeDTO);
//
//        EatWellUser recipeAuthor = (EatWellUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        newRecipe.setAuthor(recipeAuthor);
//
//        ingredientRepository.saveAll(newRecipe.getIngredients());
//        recipeRepository.save(newRecipe);
//
//        return "redirect:/recipe/new";
//    }


    @PostMapping({"/recipe/add"})
    private String saveOrUpdateRecipe(@ModelAttribute("newRecipe") Recipe recipe,
                                      BindingResult bindingResult,
                                      Model datamodel) {
        if (bindingResult.hasErrors()) {
            return "recipeCreation";
        }

        recipeRepository.save(recipe);
        return "redirect:/recipe/new";
    }

    private List<Recipe> getMyRecipes(){
        String userName = EatWellUserService.getLoggedInUsername();

        Optional<List<Recipe>> myRecipesOptional = recipeRepository.findByAuthor_Username(userName);
        List<Recipe> myRecipes = myRecipesOptional.orElseThrow(
                () -> new IllegalArgumentException(String.format(
                        "No recipes found for user %s", userName))
        );
        return myRecipes;
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

    @PostMapping("/ingredient/add")
    private String saveOrUpdateIngredient(@ModelAttribute("formIngredient") Ingredient ingredient,
                                          BindingResult result, Model datamodel) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
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
        ingredients.add(ingredient);
        return "recipeCreation";
    }



}
