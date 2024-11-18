package nl.miwnn.se14.eatwell.repositories;

import nl.miwnn.se14.eatwell.model.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Furkan Altay
 * Purpose for the class
 */
public interface QuantityRepository extends JpaRepository<Quantity, Long> {
    Optional<Quantity> findByQuantity (Integer quantity);
}
