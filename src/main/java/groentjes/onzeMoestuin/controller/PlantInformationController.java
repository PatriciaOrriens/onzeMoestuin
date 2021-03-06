package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import groentjes.onzeMoestuin.repository.PlantRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author Eric van Dalen, Wim Kruizinga, and Gjalt Wybenga
 * Controller class for managing the plant information.
 */
@Controller
public class PlantInformationController {

    @Autowired
    private PlantInformationRepository plantInformationRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private UserRepository userRepository;

    // View Plant information for user
    @GetMapping("/plantinformationoverview/{plantInfoId}")
    protected String showPlants(@PathVariable("plantInfoId") Integer plantInfoId, Model model){

        Optional<PlantInformation> foundPlantInformation = plantInformationRepository.findById(plantInfoId);
        foundPlantInformation.ifPresent(plantInformation -> model.addAttribute("plantinformation", plantInformation));
        return "plantInformationOverview";
    }

    @GetMapping(value = "/plantinformationoverview/{plantInfoId}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable("plantInfoId") Integer plantInfoId) throws IOException {

        Optional<PlantInformation> plantInformation = plantInformationRepository.findById(plantInfoId);

        InputStream input = new ByteArrayInputStream(plantInformation.get().getImage());

        return IOUtils.toByteArray(input);
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
    public String saveNewPlantInfo(@ModelAttribute("plantInformation") PlantInformation plantInformation,
                                   @RequestParam("file") MultipartFile file, BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return "uploadError";
        }

        try {
            plantInformation.setImage(file.getBytes());
            plantInformation.setImageName(file.getOriginalFilename());
            plantInformationRepository.save(plantInformation);
            return "redirect:/adminManagePlantInformation";
        } catch (Exception e) {
            return "uploadError";
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
                                     @RequestParam("file") MultipartFile file,
                                     BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "uploadError";
        }

        try {
            plantInformation.setPlantInfoId(plantInfoId);
            plantInformation.setImage(file.getBytes());
            plantInformation.setImageName(file.getOriginalFilename());
            plantInformationRepository.save(plantInformation);
            return "redirect:/adminManagePlantInformation";
        } catch (Exception e) {
            return "uploadError";
        }
    }

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
