package groentjes.onzeMoestuin.controller;


import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Patricia Orriens-Spuij
 * Controller for view in which user can create a new garden, or update his/her garden
 */
@Controller
public class NewGardenController {

    @Autowired
    private GardenRepository gardenRepository;

    @GetMapping("/garden/add")
    protected String showGardenForm(Model model) {
        model.addAttribute("garden", new Garden());

        //pass logged in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", authentication);
        return "newGarden";
    }

    @PostMapping({"/garden/add"})
    protected String saveOrUpdateGarden(@ModelAttribute("garden") Garden garden, BindingResult result) {
        if (result.hasErrors()) {
            return "newGarden";
        } else {
            gardenRepository.save(garden);
            return "redirect:/garden";
        }
    }

}
