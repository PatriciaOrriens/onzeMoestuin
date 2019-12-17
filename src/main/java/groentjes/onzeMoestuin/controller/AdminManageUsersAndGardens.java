package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Eric van Dalen
 * Controller class for the administrator to manage users (and later also the gardens)
 */
@Controller
public class AdminManageUsersAndGardens {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/adminManageUsersAndGardens")
    public String findAll(Model model) {
        model.addAttribute("allUsers", userRepository.findAll());
        return "adminManageUsersAndGardens";
    }

    @GetMapping("/user/add")
    public String createUser(){
        return "redirect:/registerUser";
    }

    @PostMapping("/returnToDashboard")
    public String returnToDashboard() {
        return "redirect:/adminDashboard";
    }
}
