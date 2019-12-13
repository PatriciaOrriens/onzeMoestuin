package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Eric van Dalen and Gjalt G. Wybenga
 * De Controller klasse voor het scherm om als aspirant gebruiker een gebruikersaccount aan te maken
 */

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registerUser")
    public String getRegisterUserForm(@ModelAttribute User user) {
        return "register";
    }

//    @PostMapping("/registerUser")
//    public String registerUser(String userName, String password) {
//        User newUser = new User();
//        newUser.setUserName(userName);
//        newUser.setPassword(password);
//        userRepository.save(newUser);
//        return "redirect:/login";
//    }

    @PostMapping("/registerUser")
    public String saveNewUser(@ModelAttribute() User user, BindingResult result) {
        userRepository.save(user);
        return "redirect:/login";
    }
}

