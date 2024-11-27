package nl.miwnn.se14.eatwell.model;

import jakarta.persistence.*;

import java.awt.desktop.PrintFilesEvent;
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

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Long getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(Long ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public int countNumberOfCapitalLetters (String ingredientName){
       int capitalLetters = 0;

        for (int i = 0; i < ingredientName.length(); i++) {
            char ch = ingredientName.charAt(i);
            if (ch >= 'A' && ch <= 'Z')
                capitalLetters++;
        }
        return capitalLetters;
    }
}
