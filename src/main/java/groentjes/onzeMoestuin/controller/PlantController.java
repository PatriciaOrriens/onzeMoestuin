package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Plant;
import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import groentjes.onzeMoestuin.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Eric van Dalen
 * Controller for managing the plants in a garden
 */
@Controller
public class PlantController {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private PlantInformationRepository plantInformationRepository;

    @GetMapping("/garden/{gardenId}/addPlant")
    public String getAddPlantForm(Model model, @PathVariable("gardenId") final Integer gardenId,
                                  @AuthenticationPrincipal User user) {

        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if (garden.isPresent()) {
            if(garden.get().isGardenMember(user)) {
                Plant plant = new Plant();
                plant.setGarden(garden.get());
                model.addAttribute("plant", plant);
                model.addAttribute("garden", garden.get());
                model.addAttribute("allPlantInformation", plantInformationRepository.findAll());
                return "addPlant";
            }
        }
            return "redirect:/";
    }

    @GetMapping("/plant/{plantId}")
    public String showPlantDetails(Model model, @PathVariable("plantId") final Integer plantId,
                                   @AuthenticationPrincipal User user) {

        Optional<Plant> plant = plantRepository.findById(plantId);
        if (plant.isPresent()) {
            if (plant.get().isOwnerOfPlant(user)) {
                model.addAttribute(plant.get());
                return "showPlant";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/garden/{gardenId}/addPlant")
    public String addPlantToGarden(@RequestParam("plantInfoId") Integer plantInfoId, @ModelAttribute("plant") Plant plant,
                                   BindingResult result, @PathVariable("gardenId") final Integer gardenId,
                                   @AuthenticationPrincipal User user) {

        Optional<PlantInformation> plantInfo  = plantInformationRepository.findById(plantInfoId);
        Optional<Garden> garden = gardenRepository.findById(gardenId);

        if (plantInfo.isPresent() && garden.isPresent()) {
            if(garden.get().isGardenMember(user)) {
                plant.setPlantInformation(plantInfo.get());
                plant.setGarden(garden.get());
                plantRepository.save(plant);
                return "redirect:/garden/" + gardenId;
            }
        }
        return "redirect:/";
    }
}
