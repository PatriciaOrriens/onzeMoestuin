package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.Optional;

/**
 * @author Eric van Dalen
 * Controller class to manage gardens
 */
@Controller
public class ManageGardenController {

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/userManageGardens")
    public String allGardensByMember(Model model, @AuthenticationPrincipal User curentUser) {
        model.addAttribute("allYourGardens", gardenRepository.findAllByGardenMembers(curentUser));
        User user = (User) userRepository.findByUsername(curentUser.getUsername()).get();
        model.addAttribute("currentUser", user);
        return "manageGarden";
    }


    @GetMapping("/user/garden/delete/{gardenId}")
    public String deleteGarden(@ModelAttribute("gardenId") Integer gardenId,
                               @AuthenticationPrincipal User user, BindingResult result) {

        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if (garden.isPresent()) {
            if(garden.get().isGardenMember(user)) {
                gardenRepository.delete(garden.get());
            }
        }
        return "redirect:/userManageGardens";
    }
}
