package nl.miwnn.se14.eatwell.model;

import jakarta.persistence.*;

import java.util.Set;

/**
 * @author Furkan Altay
 *
 */
@Entity
public class Recipe {
    @Id @GeneratedValue
    private Long recipe_id;
    private String recipe_name;
    private String description;

    public Recipe(String recipe_name, String description) {
        this.recipe_name = recipe_name;
        this.description = description;
    }

    @ManyToMany( cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "Recipe_Category", joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category>categories;

    public Recipe() {

    }

    public Long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Long recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getName() {
        return recipe_name;
    }

    public void setName(String name) {
        this.recipe_name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
