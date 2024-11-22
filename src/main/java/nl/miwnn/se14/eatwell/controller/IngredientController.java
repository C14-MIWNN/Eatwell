package nl.miwnn.se14.eatwell.controller;

import nl.miwnn.se14.eatwell.model.Ingredient;
import nl.miwnn.se14.eatwell.repositories.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
  * @author Bart Molenaars
  * Purpose for the class
  */

@Controller
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/overview/")
    private String showIngredientOverview (Model datamodel) {
        datamodel.addAttribute("allIngredients", ingredientRepository.findAll());
        return "recipeCreation";
    }

    @GetMapping("/detail/{ingredientName}")
    private String showColorDetailPage(@PathVariable("ingredientName") String ingredientName, Model model) {
        Optional<Ingredient> ingredient = ingredientRepository.findByIngredientName(ingredientName);

        if (ingredient.isEmpty()) {
            return "redirect:/color/overview";
        }

        model.addAttribute("ingredientToBeShown", ingredient.get());
        return "ingredientDetail";
    }
}
