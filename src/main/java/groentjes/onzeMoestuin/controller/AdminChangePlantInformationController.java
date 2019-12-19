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
public class AdminChangePlantInformationController {

    @Autowired
    PlantInformationRepository plantInfoRepository;

    @GetMapping("/adminchangeplantinfo")
    @Secured("ROLE_ADMIN")
    public String getPlantInfoForm(Model model) {
        model.addAttribute("plantInformation", new PlantInformation());
        return "adminChangePlantInformation";
    }

    @PostMapping("/adminchangeplantinfo")
    public String saveNewPlantInfo(@ModelAttribute() PlantInformation plantInformation, BindingResult result) {
        if (result.hasErrors()){
            return "adminChangePlantInformation";
        } else {
            plantInfoRepository.save(plantInformation);
            return "redirect:/adminManagePlantInformation";
        }
    }
}
