package groentjes.onzeMoestuin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Eric van Dalen
 * Controller klasse voor het Adminstrator dashboard om taken te selecteren.
 */
@Controller
public class AdminDashboard {
    private List<String> menuOptions = new ArrayList<>();

    @PostMapping("/onzemoestuin/admindashboard")
    public String doWelcomeMenu(String menuOption) {
        if(menuOption != null) {
            return "redirect:/" + menuOption;
        }
        return "redirect:/onzemoestuin/admindashboard";
    }

    @GetMapping("/onzemoestuin/admindashboard")
    public String getMenu(Model model) {
        menuOptions.clear();
        menuOptions.add("manageUser");
        model.addAttribute("menuOptions", menuOptions);
        return "onzemoestuin/admindashboard";
    }

    @PostMapping("/onzemoestuin/admindashboard")
    public String backToMenu() {
        return "redirect:/onzemoestuin/admindashboard";
    }
}

