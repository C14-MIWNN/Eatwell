package nl.miwnn.se14.eatwell.model;

import jakarta.persistence.*;

@Entity
public class Instructions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructions_id;

    @Column(nullable = false, name = "step_nr")
    private Integer step_nr;

    @Column(nullable = false, name = "step_description")
    private String step_description;

    @OneToOne(mappedBy = "instructions", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Recipe recipe;

}
