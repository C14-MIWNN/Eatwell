package nl.miwnn.se14.eatwell.model;

import jakarta.persistence.*;

import java.util.Set;

/**
 * @author Furkan Altay
 * Purpose for the class
 */

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long category_id;

    @Column(nullable = false)
    private String category_name;

    @ManyToMany( cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "Recipe_Category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipes;

}
