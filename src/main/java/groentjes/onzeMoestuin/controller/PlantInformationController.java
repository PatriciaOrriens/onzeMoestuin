package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Eric van Dalen, Wim Kruizinga, and Gjalt Wybenga
 * Controller class for managing the plant information.
 */
@Controller
public class PlantInformationController {

    @Autowired
    private PlantInformationRepository plantInformationRepository;

    // View Plant information for user
    @GetMapping("/plantinformationoverview/{plantInfoId}")
    protected String showPlants(@PathVariable("plantInfoId") Integer plantInfoId, Model model){
        Optional<PlantInformation> foundPlantInformation = plantInformationRepository.findById(plantInfoId);
        if (foundPlantInformation.isPresent()) {
            model.addAttribute("plantinformation", foundPlantInformation.get());
        }
        return "plantInformationOverview";
    }

    /**
     * Mappings for administrator
     */
    @GetMapping("/adminManagePlantInformation")
    @Secured("ROLE_ADMIN")
    public String managePlantInfo(Model model) {
        model.addAttribute("plantInformation", plantInformationRepository.findAll());
        return "adminManagePlantInformation";
    }

    @GetMapping("/admincreateplantinfo")
    @Secured("ROLE_ADMIN")
    public String getPlantInfoForm(Model model) {
        model.addAttribute("plantInformation", new PlantInformation());
        return "adminCreatePlantInformation";
    }

    @PostMapping("/admincreateplantinfo")
    @Secured("ROLE_ADMIN")
    public String saveNewPlantInfo(@ModelAttribute PlantInformation plantInformation,
                                   @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            plantInformation.setImage(file.getBytes());
            plantInformationRepository.save(plantInformation);
            return "redirect:/adminManagePlantInformation";
        } else {
            return "404error";
        }
    }

    @GetMapping("/plantinfo/update/{plantInfoId}")
    @Secured("ROLE_ADMIN")
    protected String showUpdatePlantInfo(@PathVariable("plantInfoId") final Integer plantInfoId, Model model){
        Optional<PlantInformation> foundPlantInformation = plantInformationRepository.findById(plantInfoId);
        if (foundPlantInformation.isPresent()) {
            model.addAttribute("plantInformation", foundPlantInformation.get());
            return "adminChangePlantInformation";
        }
        return "redirect:/adminManagePlantInformation";
    }

    @PostMapping("/plantinfo/update/{plantInfoId}")
    @Secured("ROLE_ADMIN")
    protected String updatePlantInfo(@PathVariable("plantInfoId") final Integer plantInfoId,
                                     @ModelAttribute("plantInformation") PlantInformation plantInformation,
                                     BindingResult result) {
        if (result.hasErrors()){
            return "redirect:/plantinfo/update";
        } else {
            plantInformation.setPlantInfoId(plantInfoId);
            plantInformationRepository.save(plantInformation);
            return "redirect:/adminManagePlantInformation";
        }
    }

    //TODO Ask user for confirmation
    @GetMapping("/plantinfo/delete/{plantInfoId}")
    @Secured("ROLE_ADMIN")
    public String deletePlantInformation(@ModelAttribute("plantInfoId") Integer plantInfoId, BindingResult result) {
        Optional<PlantInformation> plantInformation = plantInformationRepository.findById(plantInfoId);
        plantInformation.ifPresent(information -> plantInformationRepository.delete(information));
        return "redirect:/adminManagePlantInformation";
    }

    // Show all Tasks belonging to PlantInformation entity
    @GetMapping("/plantinfo/tasks/{plantInfoId}")
    @Secured("ROLE_ADMIN")
    public String getTasksForPlantInfo (@PathVariable("plantInfoId") final Integer plantInfoId,
                                        Model model) {
        Optional<PlantInformation> foundPlantInformation = plantInformationRepository.findById(plantInfoId);

        if(foundPlantInformation.isPresent()) {
            model.addAttribute("plantInfo", foundPlantInformation.get());
            return "adminManageTasksPlantInfo";
        } else {
            return "redirect:/adminManagePlantInformation";
        }
    }
}
