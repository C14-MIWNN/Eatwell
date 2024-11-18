package nl.miwnn.se14.eatwell.model;


import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Quantity {
    @Id
    @GeneratedValue
    private Long quantity_id;

    @Column(nullable = false, name = "quantity")
    private Integer quantity;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "Recipe_Ingredient",
            joinColumns = @JoinColumn(name = "quantity_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipes;
}
