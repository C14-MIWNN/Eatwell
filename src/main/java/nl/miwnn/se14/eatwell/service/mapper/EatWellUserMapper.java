package nl.miwnn.se14.eatwell.service.mapper;

import nl.miwnn.se14.eatwell.dto.EatWellUserDTO;
import nl.miwnn.se14.eatwell.model.EatWellUser;

/**
 * @author Bart Molenaars
 * Converts between Model classes and DTOs
 */

public class EatWellUserMapper {

    public static EatWellUser fromDTO(EatWellUserDTO dto) {
        EatWellUser user = new EatWellUser();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}
