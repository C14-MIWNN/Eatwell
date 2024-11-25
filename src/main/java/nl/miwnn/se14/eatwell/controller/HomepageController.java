package nl.miwnn.se14.eatwell.controller;


import nl.miwnn.se14.eatwell.model.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {

    @GetMapping({"/"})
    private String showHomepage(Model datamodel) {
        datamodel.addAttribute("searchForm", new Recipe());
        return "homepage";
    }
}
