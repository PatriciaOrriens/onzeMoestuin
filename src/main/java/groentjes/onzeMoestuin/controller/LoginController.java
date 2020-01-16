package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@ModelAttribute User user) {
        return "login";
    }

    @GetMapping("/loginfailed")
    public String loginfailed() {
        return "loginFailed";
    }
}