package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;


/**
 * @author Eric van Dalen
 * Controller class for managing the plant information.
 */
@Controller
public class AdminManagePlantInformation {

    @Autowired
    private PlantInformationRepository plantInformationRepository;

    @GetMapping("/adminManagePlantInformation")
    @Secured("ROLE_ADMIN")
    public String managePlantInfo(Model model) {
        model.addAttribute("plantInformation", plantInformationRepository.findAll());
        return "adminManagePlantInformation";
    }


    @GetMapping("/plantinfo/update/{plantInfoId}")
    protected String showUpdatePlantInfo(@PathVariable("plantInfoId") final Integer plantInfoId, Model model){
        Optional<PlantInformation> foundPlantInformation = plantInformationRepository.findById(plantInfoId);
        if (foundPlantInformation.isPresent()) {
            PlantInformation plantInformation = new PlantInformation();
            plantInformation.setPlantInfoId(foundPlantInformation.get().getPlantInfoId());
            model.addAttribute("plantInformation", new PlantInformation());
            return "adminChangePlantInformation";
        }
        return "redirect:/redirect:/adminManagePlantInformation";
    }

    @PostMapping("/plantinfo/update/{plantInfoId}")
    protected String updatePlantInfo(@PathVariable("plantInfoId") final Integer plantInfoId, @ModelAttribute("plantInformation") PlantInformation plantInformation, BindingResult result) {
        if (result.hasErrors()){
            return "redirect:/plantinfo/update";
        } else {
            plantInformation.setPlantInfoId(plantInfoId);
            plantInformationRepository.save(plantInformation);
            return "redirect:/adminManagePlantInformation";
        }
    }

    @GetMapping("/plantinfo/delete/{plantInfoId}")
    @Secured("ROLE_ADMIN")
    public String deletePlantInformation(@ModelAttribute("plantInfoId") Integer plantInfoId, BindingResult result) {
        Optional<PlantInformation> plantInformation = plantInformationRepository.findById(plantInfoId);
        if(plantInformation.isPresent()) {
            System.out.println(plantInformation);
            plantInformationRepository.delete(plantInformation.get());
        }
        return "redirect:/adminManagePlantInformation";
    }


}
