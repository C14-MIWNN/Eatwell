package nl.miwnn.se14.eatwell.repositories;


import nl.miwnn.se14.eatwell.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
