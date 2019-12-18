package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author Eric van Dalen
 * Controller class for managing the plant information.
 */
@Controller
public class AdminManagePlantInformation {

    @Autowired
    private PlantInformationRepository plantInformationRepository;

    @Autowired UserRepository userRepository;

    @GetMapping("/adminManagePlantInformation")
    @Secured("ROLE_ADMIN")
    public String managePlantInfo(Model model) {
        model.addAttribute("plantInformation", plantInformationRepository.findAll());
        //model.addAttribute("allUsers", userRepository.findAll());
        return "adminManagePlantInformation";
    }
}
