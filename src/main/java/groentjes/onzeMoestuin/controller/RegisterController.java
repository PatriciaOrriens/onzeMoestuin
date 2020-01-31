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

    private static final String EMPTY_STRING = "";
    private static final String ERROR_USERNAME_STRING = "Kies een andere gebruikersnaam";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GardenInvitationRepository gardenInvitationRepository;

    @GetMapping("/registerUser")
    public String getRegisterUserForm(Model model, @ModelAttribute User user, @ModelAttribute("remark") String remark,
                                      @RequestParam(name="token") Optional<String> token) {

        model.addAttribute("remark", EMPTY_STRING);
        // Check if invitation token is present
        token.ifPresent(s -> model.addAttribute("invitation", getValidInvitation(s)));
        return "register";
    }

    @PostMapping("/registerUser")
    public String saveNewUser(@Valid User user, Errors errors, Model model,  @ModelAttribute("remark") String remark,
                              @RequestParam(name ="token") Optional<String> token) {

        if (errors.hasErrors()) {
            return "redirect:/";
        } else if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("remark", ERROR_USERNAME_STRING);
            return "register";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            checkIfInvitationIsPresent(token, user);
            return "redirect:/login";
        }
    }

    private void checkIfInvitationIsPresent(Optional<String> token, User user) {
        if (token.isPresent()) {
            GardenInvitation invitation = getValidInvitation(token.get());
            if (invitation != null) {
                // update invitation: link new user
                invitation.setInvitedUser(user);
                gardenInvitationRepository.save(invitation);
            }
        }
    }

    private GardenInvitation getValidInvitation(String token) {
        Optional<GardenInvitation> gardenInvitation = gardenInvitationRepository.
                findOneByInvitationTokenAndAcceptedNull(UUID.fromString(token));
        return gardenInvitation.orElse(null);
    }
}

