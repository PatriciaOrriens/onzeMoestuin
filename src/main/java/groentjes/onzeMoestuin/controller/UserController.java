package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author Eric van Dalen
 * Controller class for the administrator to manage users.
 */
@Controller
public class UserController {

    private static final String EMPTY_STRING = "";
    private static final String ERROR_STRING = "Er is een fout opgetreden";
    private static final String ERROR_USERNAME_STRING = "Kies een andere gebruikersnaam";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/manageUsers")
    @Secured("ROLE_ADMIN")
    public String findAll(Model model) {
        model.addAttribute("allUsers", userRepository.findAll());
        return "manageUsers";
    }

    @GetMapping("/user/delete/{userName}")
    @Secured("ROLE_ADMIN")
    public String doDeleteUser(@ModelAttribute("userName") String username, BindingResult result) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            userRepository.delete(user.get());
        }
        return "redirect:/manageUsers";
    }

    @GetMapping("/user/new")
    @Secured("ROLE_ADMIN")
    protected String showNewUserForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("remark", EMPTY_STRING);
        return "adminCreateUser";
    }

    @PostMapping("/user/new")
    @Secured("ROLE_ADMIN")
    protected String saveOrUpdateUser(@ModelAttribute("user") User user, @ModelAttribute("remark") String remark,
                                      BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("remark", ERROR_STRING);
            return "adminCreateUser";
        } else if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("remark", ERROR_USERNAME_STRING);
            return "adminCreateUser";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "redirect:/manageUsers";
        }
    }
}
