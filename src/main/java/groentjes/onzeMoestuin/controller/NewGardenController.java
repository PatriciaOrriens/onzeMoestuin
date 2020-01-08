package groentjes.onzeMoestuin.controller;


import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Plant;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.PlantRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Patricia Orriens-Spuij
 * Controller for view in which user can create a new garden, or update his/her garden
 */
@Controller
public class NewGardenController {

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/garden/{gardenId}")
    protected String showGarden(Model model, @PathVariable("gardenId") final Integer gardenId) {
        Optional<Garden> garden = gardenRepository.findById(gardenId);

        if (garden.isPresent()) {
            ArrayList<Plant> plants = plantRepository.findAllByGarden(garden);
            model.addAttribute("plants", plants);
            model.addAttribute("garden", garden.get());
            return "showGarden";
        }
        return "redirect:/";
    }

    @GetMapping("/garden/add")
    protected String showGardenForm(Model model, @AuthenticationPrincipal User user) {
        Garden garden = new Garden();
        garden.setUser(user);
        model.addAttribute("garden", garden);

        return "newGarden";
    }

    @PostMapping({"/garden/add"})
    protected String saveOrUpdateGarden(@ModelAttribute("garden") Garden garden, BindingResult result,
                                        @AuthenticationPrincipal User user) {

        if (result.hasErrors()) {
            return "newGarden";
        } else {
            // Retrieve complete User object from database to be able to add member to garden
            User owner = userRepository.getOne(user.getUserId());
            garden.addGardenMember(owner);
            garden = gardenRepository.save(garden);

            return "redirect:/garden/" + garden.getGardenId();
        }
    }
}
