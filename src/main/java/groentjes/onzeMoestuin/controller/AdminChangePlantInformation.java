package groentjes.onzeMoestuin.controller;


import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @author Patricia Orriens-Spuij and Gjalt
 *
 */
@Controller
public class AdminChangePlantInformation {

    @Autowired
    PlantInformationRepository plantInfoRepository;

    @GetMapping("/adminchangeplantinfo")
    public String getPlantInfoForm(Model model) {
        model.addAttribute("plantInformation", new PlantInformation());
/*
        String[] monthList = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
        "October", "November", "December"};
        model.addAttribute("monthList", monthList);

      //  String[] lightingList = {"full sun", "sun", "partial sun/shade", "shade", "full shade"};
      //  model.addAttribute("lightingList", lightingList);
*/
        return "adminChangePlantInformation";
    }

    @PostMapping("/adminchangeplantinfo")
    public String saveNewPlantInfo(@ModelAttribute() PlantInformation plantInformation, BindingResult result) {
        plantInfoRepository.save(plantInformation);
        return "adminChangePlantInformation";
    }

}
