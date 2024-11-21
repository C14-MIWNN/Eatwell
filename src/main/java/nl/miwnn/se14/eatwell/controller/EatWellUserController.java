package nl.miwnn.se14.eatwell.controller;

import jakarta.validation.Valid;
import nl.miwnn.se14.eatwell.dto.EatWellUserDTO;
import nl.miwnn.se14.eatwell.service.EatWellUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Bart Molenaars
 * Handles requests related to user class.
 */

@Controller
@RequestMapping("/user")
public class EatWellUserController {
    private final EatWellUserService eatWellUserService;

    public EatWellUserController(EatWellUserService eatWellUserService){
        this.eatWellUserService = eatWellUserService;
    }

    @GetMapping("/overview")
    private String showUserOverview(Model datamodel){
        datamodel.addAttribute("allUsers", eatWellUserService.getAllUsers());
        datamodel.addAttribute("formUser", new EatWellUserDTO());
        datamodel.addAttribute("formModalHidden", true);

        return "userOverview";
    }

    @PostMapping("/save")
    private String saveOrUpdateUser(@ModelAttribute("formUser") @Valid EatWellUserDTO userDtoToBeSaved,
                                    BindingResult result,
                                    Model datamodel) {
        ;
        if (eatWellUserService.usernameInUse(userDtoToBeSaved.getUsername())) {
            result.rejectValue("username", "duplicate", "This username is not available");
        }

        if (!userDtoToBeSaved.getPassword().equals(userDtoToBeSaved.getPasswordConfirm())){
            result.rejectValue("password", "no.match", "The passwords do not match");
        }

        if (result.hasErrors()) {
            datamodel.addAttribute("allUsers", eatWellUserService.getAllUsers());
            datamodel.addAttribute("formModalHidden", false);
            return "userOverview";
        }

        eatWellUserService.save(userDtoToBeSaved);
        return "redirect:/user/overview";
    }
}
