package nl.miwnn.se14.eatwell.service;


import nl.miwnn.se14.eatwell.dto.EatWellUserDTO;
import nl.miwnn.se14.eatwell.model.EatWellUser;
import nl.miwnn.se14.eatwell.repositories.EatWellUserRepository;
import nl.miwnn.se14.eatwell.service.mapper.EatWellUserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Bart Molenaars
 * Purpose for the class
 */

@Service
public class EatWellUserService implements UserDetailsService {
    private final EatWellUserRepository eatWellUserRepository;
    private final PasswordEncoder passwordEncoder;

    public EatWellUserService(EatWellUserRepository eatWellUserRepository, PasswordEncoder passwordEncoder) {
        this.eatWellUserRepository = eatWellUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return eatWellUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s was not found.", username)));
    }

    public void save(EatWellUserDTO userDTO){
        save(EatWellUserMapper.fromDTO(userDTO));
    }

    public static String getLoggedInUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public boolean usernameInUse(String username) {
        return eatWellUserRepository.findByUsername(username).isPresent();
    }

    public void save(EatWellUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        eatWellUserRepository.save(user);
    }

    public List<EatWellUser> getAllUsers() {
        return eatWellUserRepository.findAll();
    }
}
