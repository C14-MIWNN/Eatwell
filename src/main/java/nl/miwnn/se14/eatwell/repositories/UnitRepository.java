package nl.miwnn.se14.eatwell.repositories;


import nl.miwnn.se14.eatwell.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    Optional<Unit> findByUnitName (String unitName);
}
