package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Eric van Dalen and Gjalt G. Wybenga
 * Controller class for a screen to register as a user and create your own user account
 */

@Controller
public class RegisterController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registerUser")
    public String getRegisterUserForm(@ModelAttribute User user) {
        return "register";
    }

    @PostMapping("/registerUser")
    public String saveNewUser(@ModelAttribute() User user, BindingResult result) {
        if(result.hasErrors()){
            return "registerUser";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "redirect:/login";
        }
    }
}

