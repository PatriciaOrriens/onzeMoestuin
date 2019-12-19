package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Plant;
import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PlantController {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @GetMapping("/garden/{gardenId}/addPlant")
    public String getAddPlantForm(Model model, @PathVariable("gardenId") final Integer gardenId) {
        model.addAttribute("plant", new Plant());
        model.addAttribute("garden", gardenRepository.findById(gardenId));
        return "addPlant";
    }

}
