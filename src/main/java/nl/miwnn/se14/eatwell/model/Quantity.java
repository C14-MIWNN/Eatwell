package nl.miwnn.se14.eatwell.model;


import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Quantity {
    @Id
    @GeneratedValue
    private Long quantity_id;

    @Column(nullable = true, name = "quantity")
    private Integer quantity;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "Recipe_Quantity",
            joinColumns = @JoinColumn(name = "quantity_id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "recipe_id", nullable = true))
    private Set<Recipe> recipes;
}
