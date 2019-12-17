package groentjes.onzeMoestuin.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Eric van Dalen
 * Controller class for the adminstrator dashboard to select administrator tasks.
 */
@Controller
public class AdminDashboardController {

    @GetMapping("/adminDashboard")
    @Secured("ROLE_ADMIN")
    public String getMenu() {
        return "adminDashboard";
    }
}

