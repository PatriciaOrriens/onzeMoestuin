package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Eric van Dalen
 * De Controller klasse voor het scherm om als aspirant gebruiker een gebruikersaccount aan te maken
 */
@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registerUser")
    public String registerUser(String userName, String password) {
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(password);
        userRepository.save(newUser);
        return "redirect:/login";
    }
}

