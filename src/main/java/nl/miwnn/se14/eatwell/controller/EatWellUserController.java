package nl.miwnn.se14.eatwell.controller;

import nl.miwnn.se14.eatwell.model.EatWellUser;
import nl.miwnn.se14.eatwell.service.EatWellUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        datamodel.addAttribute("formUser", new EatWellUser());

        return "userOverview";
    }
}
