package nl.miwnn.se14.eatwell.controller;

import nl.miwnn.se14.eatwell.model.Category;
import nl.miwnn.se14.eatwell.model.Recipe;
import nl.miwnn.se14.eatwell.repositories.CategoryRepository;
import nl.miwnn.se14.eatwell.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public CategoryController(CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping({"/category/overview"})
    private String showCategoryOverview(Model datamodel) {
        datamodel.addAttribute("searchForm", new Recipe());
        datamodel.addAttribute("allRecipes", recipeRepository.findAll());
        datamodel.addAttribute("allCategories", categoryRepository.findAll());
        return "categoryOverview";
    }

    @GetMapping("/category/{categoryName}")
    public String showCategoryDetails(@PathVariable("categoryName") String categoryName, Model datamodel) {
        Optional<Category> categoryOptional = categoryRepository.findByCategoryName(categoryName);

        if (categoryOptional.isEmpty()) {
            datamodel.addAttribute("category", new Category());
            return "categoryDetails";
        }
        datamodel.addAttribute("searchForm", new Recipe());
        datamodel.addAttribute("category", categoryOptional.get());
        return "categoryDetails";
    }


}
