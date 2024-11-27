package nl.miwnn.se14.eatwell.controller;

import nl.miwnn.se14.eatwell.model.Ingredient;
import nl.miwnn.se14.eatwell.repositories.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    private void setupIngredientOverview(Model datamodel, boolean formModalHidden) {
        datamodel.addAttribute("allIngredients", ingredientRepository.findAll());
        if (!datamodel.containsAttribute("formIngredient")){
            datamodel.addAttribute("formIngredient", new Ingredient());
        }

        datamodel.addAttribute("formModalHidden", formModalHidden);
    }

    @GetMapping("/overview")
    private String showIngredientOverview (Model datamodel) {

        setupIngredientOverview(datamodel, true);
        return "ingredientOverview";
    }

    @GetMapping("/detail/{ingredientName}")
    private String showIngredientDetailPage(@PathVariable("ingredientName") String ingredientName, Model model) {
        Optional<Ingredient> ingredient = ingredientRepository.findByIngredientName(ingredientName);

        if (ingredient.isEmpty()) {
            return "redirect:/ingredient/overview";
        }

        model.addAttribute("ingredientToBeShown", ingredient.get());
        return "ingredientDetail";
    }

    @PostMapping("/add")
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
            return "ingredientOverview";
        }
        ingredientRepository.save(ingredient);
        return "redirect:/ingredient/overview";
    }

}
