package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.GardenInvitation;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenInvitationRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Wim Kruizinga, Eric van Dalen and Gjalt G. Wybenga
 * Controller class for a screen to register as a user and create your own user account
 */

@Controller
public class RegisterController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GardenInvitationRepository gardenInvitationRepository;

    @GetMapping("/registerUser")
    public String getRegisterUserForm(Model model, @ModelAttribute User user,
                                      @RequestParam(name="token") Optional<String> token) {
        // Check if invitation token is present
        token.ifPresent(s -> model.addAttribute("invitation", getValidInvitation(s)));
        return "register";
    }

    @PostMapping("/registerUser")
    public String saveNewUser(@Valid User user, Errors errors, @RequestParam(name ="token") Optional<String> token) {

        if (errors.hasErrors()) {
            return "redirect:/";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            // Check if invitation token is present
            if (token.isPresent()) {
                Optional<GardenInvitation> gardenInvitation = gardenInvitationRepository.
                        findOneByInvitationToken(UUID.fromString(token.get()));
                if (gardenInvitation.isPresent()) {
                    // update invitation: link new user
                    gardenInvitation.get().setInvitedUser(user);
                    gardenInvitationRepository.save(gardenInvitation.get());
                }
            }
            return "redirect:/login";
        }
    }

    private GardenInvitation getValidInvitation(String token) {
        Optional<GardenInvitation> gardenInvitation = gardenInvitationRepository.
                findOneByInvitationTokenAndAcceptedNull(UUID.fromString(token));
        return gardenInvitation.orElse(null);
    }
}

