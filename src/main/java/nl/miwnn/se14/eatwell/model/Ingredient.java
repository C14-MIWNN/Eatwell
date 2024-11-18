package nl.miwnn.se14.eatwell.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue
    private Long ingredient_id;

    @Column(nullable = false, name = "ingredient_name")
    private String ingredientName;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "Recipe_Ingredient",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipes;

}
