package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@ModelAttribute User user, Model model,
                        @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        if(error != null) {
            errorMessage = "Gebruikersnaam of wachtwoord onjuist";
        }
        model.addAttribute("error", errorMessage);
        return "login";
    }

    @GetMapping("/loginsuccess")
    public String loginsuccessful() {
        return "loginsuccess";
    }
}