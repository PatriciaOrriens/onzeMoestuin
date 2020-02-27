package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.GardenInvitation;
import groentjes.onzeMoestuin.model.Role;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenInvitationRepository;
import groentjes.onzeMoestuin.repository.RoleRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Wim Kruizinga, Eric van Dalen and Gjalt G. Wybenga
 * Controller class for a screen to register as a user and create your own user account
 */

@Controller
public class RegisterController {

    private static final int START_REPLACE = 30;
    private static final int END_REPLACE = 35;
    private static final String ROLE_USER = "ROLE_USER";
    private static final  String REPLACE = " en ";
    private static final String ERROR_USERNAME_STRING = "Kies een andere gebruikersnaam";
    private static final String ERROR_EMAIL_STRING = "Kies een ander E-mailadres";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GardenInvitationRepository gardenInvitationRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/registerUser")
    public String getRegisterUserForm(Model model, @ModelAttribute User user,
                                      @RequestParam(name="token") Optional<String> token) {

        // Add invitation token if present
        token.ifPresent(s -> model.addAttribute("invitation", getValidInvitation(s)));
        return "register";
    }

    @PostMapping("/registerUser")
    public String saveNewUser(@Valid User user, Errors errors, Model model, @ModelAttribute("remark") String remark,
                              @RequestParam(name ="token") Optional<String> token) {

        boolean isExistingName = userRepository.findByUsername(user.getUsername()).isPresent();
        boolean isExistingEmail = userRepository.findByEmail(user.getEmail()).isPresent();
        if(errors.hasErrors()) {
            return "register";
        } else if (isExistingName || isExistingEmail) {
            checkForInvalidInput(model, user);
            return "register";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            (roleRepository.findByRoleName(ROLE_USER)).ifPresent(role -> user.getRole().add(role));
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

    private void checkForInvalidInput(Model model, User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("remark", ERROR_USERNAME_STRING);
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                StringBuilder stringBuilder = new StringBuilder(ERROR_USERNAME_STRING);
                stringBuilder.append(ERROR_EMAIL_STRING).replace(START_REPLACE, END_REPLACE, REPLACE);
                model.addAttribute("remark", stringBuilder);
            }
        } else if (userRepository.findByEmail(user.getEmail()).isPresent())  {
            model.addAttribute("remark", ERROR_EMAIL_STRING);
        }
    }
}

