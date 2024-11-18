package nl.miwnn.se14.eatwell.model;

import jakarta.persistence.*;
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
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients;


    public Recipe() {
    }

    public Recipe(String recipe_name, String description) {
        this.name = recipe_name;
        this.description = description;
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
}
