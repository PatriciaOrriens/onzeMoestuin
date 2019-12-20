package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Plant;
import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import groentjes.onzeMoestuin.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PlantController {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private PlantInformationRepository plantInformationRepository;

    @GetMapping("/garden/{gardenId}/addPlant")
    public String getAddPlantForm(Model model, @PathVariable("gardenId") final Integer gardenId) {
        Optional<Garden> garden  = gardenRepository.findById(gardenId);

        if (garden.isPresent()) {
            Plant plant = new Plant();
            plant.setGarden(garden.get());
            model.addAttribute("plant", plant);
            model.addAttribute("garden", garden.get());
            model.addAttribute("allPlantInformation", plantInformationRepository.findAll());
            return "addPlant";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/garden/{gardenId}/addPlant")
    public String addPlantToGarden(@RequestParam("plantInfoId") Integer plantInfoId, @ModelAttribute("plant") Plant plant,
                                   BindingResult result, @PathVariable("gardenId") final Integer gardenId) {

        Optional<PlantInformation> plantInfo  = plantInformationRepository.findById(plantInfoId);
        Optional<Garden> garden = gardenRepository.findById(gardenId);

        if (plantInfo.isPresent() && garden.isPresent()) {
            plant.setPlantInformation(plantInfo.get());
            plant.setGarden(garden.get());
            plantRepository.save(plant);
            return "redirect:/garden/" + gardenId;
        } else {
            return "redirect:/";
        }
    }
}
