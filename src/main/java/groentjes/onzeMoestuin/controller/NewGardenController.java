package groentjes.onzeMoestuin.controller;


import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Patricia Orriens-Spuij
 * Controller for view in which user can create a new garden, or update his/her garden
 */
public class NewGardenController {

    @Autowired
    GardenRepository gardenRepository;

    @GetMapping("/garden/add")
    protected String showGardenForm(Model model) {
        model.addAttribute("garden", new Garden());
        model.addAttribute("user", new User());
        return "newGardenForm";
    }

    @PostMapping({"/garden/add"})
    protected String saveOrUpdateGarden(@ModelAttribute("garden") Garden garden, BindingResult result) {
        if (result.hasErrors()) {
            return "newGardenForm";
        } else {
            gardenRepository.save(garden);
            return "redirect:/garden";
        }
    }

}
