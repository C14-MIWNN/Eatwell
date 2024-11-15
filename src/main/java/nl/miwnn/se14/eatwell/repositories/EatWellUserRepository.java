package nl.miwnn.se14.eatwell.repositories;

import nl.miwnn.se14.eatwell.model.EatWellUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EatWellUserRepository extends JpaRepository<EatWellUser, Long> {
    Optional<EatWellUser> findByUsername(String username);
}
