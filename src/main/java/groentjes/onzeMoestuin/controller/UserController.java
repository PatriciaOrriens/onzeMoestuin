package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.RoleRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import groentjes.onzeMoestuin.model.Role;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Eric van Dalen
 * Controller class for the administrator to manage users.
 */
@Controller
public class UserController {

    private static final int START_REPLACE = 30;
    private static final int END_REPLACE = 35;
    private static final  String REPLACE = " en ";
    private static final String ERROR_STRING = "Er is een fout opgetreden";
    private static final String ERROR_USERNAME_STRING = "Kies een andere gebruikersnaam";
    private static final String ERROR_EMAIL_STRING = "Kies een ander E-mailadres";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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
        return "adminCreateUser";
    }

    @PostMapping("/user/new")
    @Secured("ROLE_ADMIN")
    protected String saveOrUpdateUser(@Valid User user, Role role,
                                      Errors errors,
                                      @ModelAttribute("remark") String remark,
                                      Model model, BindingResult bindingResult){

        boolean isResultError = errors.hasErrors();
        boolean isExistingName = userRepository.findByUsername(user.getUsername()).isPresent();
        boolean isExistingEmail = userRepository.findByEmail(user.getEmail()).isPresent();
        if(isResultError || isExistingName || isExistingEmail) {
            checkForInvalidInput(model, user, isResultError);
            return "adminCreateUser";
        } else {
            model.addAttribute(role.getRoleName());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRole().add(role);
            userRepository.save(user);
            return "redirect:/manageUsers";
        }
    }

    private void checkForInvalidInput(Model model, User user, boolean isResultError) {
        if(isResultError){
            model.addAttribute("remark", ERROR_STRING);
        } else if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("remark", ERROR_USERNAME_STRING);
            checkEmailInput(model, user);
        } else if (userRepository.findByEmail(user.getEmail()).isPresent())  {
            model.addAttribute("remark", ERROR_EMAIL_STRING);
        }
    }

    private void checkEmailInput(Model model, User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            StringBuilder stringBuilder = new StringBuilder(ERROR_USERNAME_STRING);
            stringBuilder.append(ERROR_EMAIL_STRING).replace(START_REPLACE, END_REPLACE, REPLACE);
            model.addAttribute("remark", stringBuilder);
        }
    }
}
