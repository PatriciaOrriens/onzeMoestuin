package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Role;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.RoleRepository;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * @author Eric van Dalen
 * Controller class for the administrator to manage users (and later also the gardens)
 */
@Controller
public class UserController {

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
            System.out.println(user);
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
    protected String saveOrUpdateUser(@ModelAttribute("user") User user,
                                      Role role, Model model,
                                      BindingResult result){
        if(result.hasErrors()){
            return "adminCreateUser";
        } else {
            model.addAttribute(role.getRoleName());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRole().add(role);
            userRepository.save(user);
            return "redirect:/manageUsers";
        }
    }
}
