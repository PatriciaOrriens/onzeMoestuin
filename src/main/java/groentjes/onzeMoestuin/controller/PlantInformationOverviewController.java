package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * @author Eric van Dalen and Gjalt G. Wybenga
 * Controller for displaying plant information
 */
@Controller
public class PlantInformationOverviewController {

    @Autowired
    private PlantInformationRepository plantInformationRepository;

    @GetMapping("/plantinformationoverview/{plantInfoId}")
    protected String showPlants(@PathVariable("plantInfoId") Integer plantInfoId, Model model){
        Optional<PlantInformation> foundPlantInformation = plantInformationRepository.findById(plantInfoId);
        if (foundPlantInformation.isPresent()) {
            model.addAttribute("plantinformation", foundPlantInformation.get());
        }
        return "plantInformationOverview";
    }
}
