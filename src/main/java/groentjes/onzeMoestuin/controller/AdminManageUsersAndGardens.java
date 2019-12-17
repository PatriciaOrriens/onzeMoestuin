package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Eric van Dalen
 * Controller class for the administrator to manage users (and later also the gardens)
 */
@Controller
public class AdminManageUsersAndGardens {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/adminManageUsersAndGardens")
    @Secured("ROLE_ADMIN")
    public String findAll(Model model) {
        model.addAttribute("allUsers", userRepository.findAll());
        return "adminManageUsersAndGardens";
    }

    @GetMapping("/user/add")
    public String createUser(){
        return "redirect:/registerUser";
    }

}
