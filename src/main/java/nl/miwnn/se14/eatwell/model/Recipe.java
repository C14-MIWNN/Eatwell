package nl.miwnn.se14.eatwell.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

/**
 * @author Furkan Altay
 *
 */

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private Long recipe_id;

    @Column(name = "recipe_name")
    private String name;

    @Column
    private String description;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "Recipe_Category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "Recipe_Ingredient",
            joinColumns = @JoinColumn(name = "recipe_id",nullable = true),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id",nullable = true))
    private List<Ingredient> ingredients;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "Recipe_Quantity",
            joinColumns = @JoinColumn(name = "recipe_id",nullable = true),
            inverseJoinColumns = @JoinColumn(name = "quantity_id",nullable = true))
    private List<Quantity> quantities;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "Recipe_Unit",
            joinColumns = @JoinColumn(name = "recipe_id" ,nullable = true),
            inverseJoinColumns = @JoinColumn(name = "unit_id" ,nullable = true))
    private Set<Unit> units;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "instruction_id" ,referencedColumnName = "instructions_id",  unique = true)
    private Instructions instructions;

    @ManyToOne
    private EatWellUser author;

    public Recipe() {
    }

    public Recipe(String recipe_name, String description) {
        this.name = recipe_name;
        this.description = description;
    }

    public EatWellUser getAuthor() {
        return author;
    }

    public void setAuthor(EatWellUser author) {
        this.author = author;
    }

    public Long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Long recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getRecipe_name() {
        return name;
    }

    public void setRecipe_name(String recipe_name) {
        this.name = recipe_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Quantity> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Quantity> quantities) {
        this.quantities = quantities;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }

    public Instructions getInstructions() {
        return instructions;
    }

    public void setInstructions(Instructions instructions) {
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}