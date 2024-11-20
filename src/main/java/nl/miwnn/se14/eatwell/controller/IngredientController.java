package nl.miwnn.se14.eatwell.controller;

import nl.miwnn.se14.eatwell.model.Ingredient;
import nl.miwnn.se14.eatwell.repositories.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/new")
    private String showIngredientOverview(Model datamodel) {
        datamodel.addAttribute("allIngredients", ingredientRepository.findAll());
        datamodel.addAttribute("formIngredient", new Ingredient());
        datamodel.addAttribute("formModalHidden", true);

        return "recipeCreation";
    }

    @PostMapping("/save")
    private String saveOrUpdateIngredient(@ModelAttribute("formIngredient") Ingredient ingredient,
                                          BindingResult result) {
        Optional<Ingredient> sameName = ingredientRepository.findByIngredientName(ingredient.getIngredientName());
        if (sameName.isPresent() && !sameName.get().getIngredient_id().equals(ingredient.getIngredient_id())) {
            result.addError(new FieldError("formIngredient",
                    "name",
                    "this ingredient has already been added"));
        }

        if (result.hasErrors()){
            return "ingredientForm";
        }

        ingredientRepository.save(ingredient);
        return "redirect:/recipe/new";
    }

}
