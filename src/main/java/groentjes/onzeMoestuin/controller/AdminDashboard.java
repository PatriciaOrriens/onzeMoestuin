package groentjes.onzeMoestuin.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Eric van Dalen
 * Controller class for the adminstrator dashboard to select administrator tasks.
 */
@Controller
public class AdminDashboard {
    private List<String> menuOptions = new ArrayList<>();



    @PostMapping("/doTask")
    public String doWelcomeMenu(String menuOption) {
        if(menuOption != null) {
            return "redirect:/" + menuOption;
        }
        return "redirect:/adminDashboard";
    }

    @GetMapping("/adminDashboard")
    @Secured("ROLE_ADMIN")
    public String getMenu(Model model) {
        menuOptions.clear();
        menuOptions.add("adminManageUsersAndGardens");
        model.addAttribute("menuOptions", menuOptions);
        return "adminDashboard";
    }


}

