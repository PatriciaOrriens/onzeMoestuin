package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Task;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author Eric van Dalen, Wim Kruizinga
 * Controller class to manage gardens
 */
@Controller
public class ManageGardenController {

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/userManageGardens")
    public String allGardensByMember(Model model, @AuthenticationPrincipal User currentUser) {
        model.addAttribute("allYourGardens", gardenRepository.findAllByGardenMembers(currentUser));
        User user = (User) userRepository.findByUsername(currentUser.getUsername()).get();
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

    @GetMapping("/garden/update/{gardenId}")
    protected String showUpdateGarden(@PathVariable("gardenId") final Integer gardenId, Model model){
        Optional<Garden> foundGarden = gardenRepository.findById(gardenId);
        if (foundGarden.isPresent()) {
            model.addAttribute("garden", foundGarden.get());
            return "userChangeGarden";
        }
        return "redirect:/userManageGardens";
    }

    @PostMapping("/garden/update/{gardenId}")
    protected String updateGarden(@ModelAttribute("garden") Garden garden,
                                  @AuthenticationPrincipal User user, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/garden/update";
        } else {
            User owner = userRepository.getOne(user.getUserId());
            garden.addGardenMember(owner);
            gardenRepository.save(garden);
            return "redirect:/userManageGardens";
        }
    }
}
