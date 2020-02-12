package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Plant;
import groentjes.onzeMoestuin.model.TaskPlant;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.PlantRepository;
import groentjes.onzeMoestuin.repository.TaskPlantRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

/**
 * @author Patricia Orriens-Spuij and Eric van Dalen
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

    @Autowired
    private TaskPlantRepository taskPlantRepository;

    @GetMapping("/garden/{gardenId}")
    protected String showGarden(Model model, @PathVariable("gardenId") final Integer gardenId) {

        User user = getUser();

        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if (garden.isPresent()) {
            if(garden.get().isGardenMember(user)) {
                addAttributesToShowGardenView(garden, model);
                return "showGarden";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/garden/add")
    protected String showGardenForm(Model model) {

        User user = getUser();

        Garden garden = new Garden();
        garden.setUser(user);
        model.addAttribute("garden", garden);

        return "newGarden";
    }

    @PostMapping({"/garden/add"})
    protected String saveOrUpdateGarden(@ModelAttribute("garden") Garden garden, BindingResult result) {

        if (result.hasErrors()) {
            return "newGarden";
        } else {
            // Retrieve complete User object from database to be able to add member to garden
            User user = getUser();

            garden.addGardenMember(user);
            garden = gardenRepository.save(garden);

            return "redirect:/garden/" + garden.getGardenId();
        }
    }

    private void addAttributesToShowGardenView(Optional<Garden> garden, Model model) {
        ArrayList<Plant> plants = plantRepository.findAllByGarden(garden);
        ArrayList<TaskPlant> taskPlants = new ArrayList<>();
        for (Plant plant : plants) {
            ArrayList<TaskPlant> tasksForPlant = taskPlantRepository.findNotCompletedTaskPlant(plant);
            taskPlants.addAll(tasksForPlant);
        }
        Collections.sort(taskPlants);
        model.addAttribute("taskPlants", taskPlants);
        model.addAttribute("plants", plants);
        model.addAttribute("garden", garden.get());
    }

    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByUsername(currentPrincipalName).orElseThrow(() -> new UsernameNotFoundException(currentPrincipalName));
    }
}
