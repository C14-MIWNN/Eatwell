package nl.miwnn.se14.eatwell.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Unit {
    @Id
    @GeneratedValue
    private Long unit_id;

    @Column(nullable = false, name = "unit_name")
    private String unitName;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "Recipe_Ingredient",
            joinColumns = @JoinColumn(name = "unit_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipes;
}
