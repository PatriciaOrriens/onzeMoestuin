package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
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
 * Controller class to welcome user and to manage the gardens
 */
@Controller
public class ManageGardenController {

    @Autowired
    private GardenRepository gardenRepository;

    @GetMapping("/userManageGardens")
    public String findAllYourGardens(Model model, @AuthenticationPrincipal User gardenuser) {
            model.addAttribute("allYourGardens", gardenRepository.findAllByUser(gardenuser));
        return "/manageGarden";
    }

    @GetMapping("/user/garden/delete/{gardenId}")
    public String deleteGarden(@ModelAttribute("gardenId") Integer gardenId, BindingResult result) {
        Optional<Garden> garden = gardenRepository.findById(gardenId);
        garden.ifPresent(information -> gardenRepository.delete(information));
        return "redirect:/userManageGardens";
    }
}
