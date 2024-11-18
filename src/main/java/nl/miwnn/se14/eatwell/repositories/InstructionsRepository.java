package nl.miwnn.se14.eatwell.repositories;

import nl.miwnn.se14.eatwell.model.Instructions;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Furkan Altay
 * Purpose for the class
 */
public interface InstructionsRepository extends JpaRepository<Instructions, Long> {
}
