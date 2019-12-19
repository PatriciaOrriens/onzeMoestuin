package groentjes.onzeMoestuin.controller;


import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @author Patricia Orriens-Spuij and Gjalt G. Wybenga
 *
 */
@Controller
public class AdminCreatePlantInformationController {

    @Autowired
    PlantInformationRepository plantInfoRepository;

    @GetMapping("/admincreateplantinfo")
    @Secured("ROLE_ADMIN")
    public String getPlantInfoForm(Model model) {
        model.addAttribute("plantInformation", new PlantInformation());
        return "adminCreatePlantInformation";
    }

    @PostMapping("/admincreateplantinfo")
    public String saveNewPlantInfo(@ModelAttribute() PlantInformation plantInformation, BindingResult result) {
        if (result.hasErrors()){
            return "adminCreatePlantInformation";
        } else {
            plantInfoRepository.save(plantInformation);
            return "redirect:/adminManagePlantInformation";
        }
    }
}
