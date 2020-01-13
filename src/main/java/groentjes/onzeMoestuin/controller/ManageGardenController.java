package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Plant;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
    public String findAllYourGardens(Model model, @AuthenticationPrincipal User gardenuser) {
            model.addAttribute("allYourGardens", gardenRepository.findAllByUser(gardenuser));
        return "/manageGarden";
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

    @GetMapping("/garden/{gardenId}/invite")
    protected String inviteToGarden(Model model, @PathVariable("gardenId") final Integer gardenId,
                                    @RequestParam(value = "search") Optional<String> search) {
        Optional<Garden> garden = gardenRepository.findById(gardenId);

        if (garden.isPresent()) {
            model.addAttribute("garden", garden.get());
            if (search.isPresent()) {
                Optional<User> foundUser = userRepository.findByUsername(search.get());
                foundUser.ifPresent(user -> model.addAttribute("foundUser", user));
            }
            return "inviteGardenMember";
        }
        return "redirect:/";
    }
}