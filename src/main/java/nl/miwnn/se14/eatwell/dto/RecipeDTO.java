package nl.miwnn.se14.eatwell.dto;

import nl.miwnn.se14.eatwell.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Bart Molenaars
 * Captures information for the recipe creation form
 */

public class RecipeDTO {
private Long recipe_id;
private String name;
private String description;
private Set<Category> category;
private ArrayList<Ingredient> ingredients;
private List<Quantity> quantities;
private Instructions instructions;
private EatWellUser author;

public RecipeDTO(){

}

    public Long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Long recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Quantity> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Quantity> quantities) {
        this.quantities = quantities;
    }

    public Instructions getInstructions() {
        return instructions;
    }

    public void setInstructions(Instructions instructions) {
        this.instructions = instructions;
    }

    public EatWellUser getAuthor() {
        return author;
    }

    public void setAuthor(EatWellUser author) {
        this.author = author;
    }
}
