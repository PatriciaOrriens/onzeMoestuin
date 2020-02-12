package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.*;
import groentjes.onzeMoestuin.repository.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Eric van Dalen, Gjalt Wybenga and Patricia Orriens
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

    @Autowired
    private TaskPlantRepository taskPlantRepository;

    @Autowired
    private TaskPlantInfoRepository taskPlantInfoRepository;

    @GetMapping("/garden/{gardenId}/addPlant")
    public String getAddPlantForm(Model model, @PathVariable("gardenId") final Integer gardenId,
                                  @AuthenticationPrincipal User user) {

        Optional<Garden> garden = gardenRepository.findById(gardenId);
        List<PlantInformation> allPlantInformation = plantInformationRepository.findAll();
        if (garden.isPresent() && (allPlantInformation.size() != 0)) {
            if(garden.get().isGardenMember(user)) {
                Plant plant = new Plant();
                plant.setGarden(garden.get());
                model.addAttribute("plant", plant);
                model.addAttribute("garden", garden.get());
                model.addAttribute("allPlantInformation", allPlantInformation);
                return "addPlant";
            }
        }
        return "redirect:/";
    }

    @GetMapping(value = "/plant/{plantId}")
    public String showPlantDetails(@PathVariable("plantId") final Integer plantId, Model model, @AuthenticationPrincipal User user) throws IOException {

        Optional<Plant> plant = plantRepository.findById(plantId);

        if (plant.isPresent()) {
            if (plant.get().isOwnerOfPlant(user)) {
                model.addAttribute(plant.get());
                return "showPlant";
            }
        }
        return "redirect:/";
    }

    @GetMapping(value = "/plant/{plantId}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable("plantId") final Integer plantId) throws IOException {

        Optional<Plant> plant = plantRepository.findById(plantId);

        InputStream input = new ByteArrayInputStream(plant.get().getPlantInformation().getImage());
        return IOUtils.toByteArray(input);
    }

    // mapping activated after click on button in addPlant.
    @GetMapping("/garden/{gardenId}/addPlant/{plantInfoId}")
    public String addPlantToGarden(@PathVariable("gardenId") final Integer gardenId,
                                   @PathVariable("plantInfoId") final Integer plantInfoId,
                                   @AuthenticationPrincipal User user) {

        Plant newPlant = new Plant();
        Optional<PlantInformation> plantInfo  = plantInformationRepository.findById(plantInfoId);
        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if (plantInfo.isPresent() && garden.isPresent()) {
            if(garden.get().isGardenMember(user)) {
                savePlantAndTaskPlant(plantInfo, garden, newPlant);
                return "redirect:/garden/" + gardenId + "/addPlant";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/plant/delete/{plant.plantId}")
    public String deletePlant(@ModelAttribute("plant.plantId") Integer plantId,
                              BindingResult result) {

        Optional<Plant> plant = plantRepository.findById(plantId);
        if (plant.isPresent()) {
                plantRepository.delete(plant.get());
        }
        return "redirect:/userManageGardens";
    }

    private void savePlantAndTaskPlant(Optional<PlantInformation> plantInfo, Optional<Garden> garden, Plant plant) {
        plant.setPlantInformation(plantInfo.get());
        plant.setGarden(garden.get());
        plant.setStartDate(new Date());
        plantRepository.save(plant);
        ArrayList<TaskPlantInfo> taskPlantInfos;
        taskPlantInfos = taskPlantInfoRepository.findAllByPlantInformation(plant.getPlantInformation());
        for (TaskPlantInfo taskInfoPlant : taskPlantInfos) {
            TaskPlant taskPlant = new TaskPlant();
            taskPlant.setPlant(plant);
            taskPlant.setTaskPlantInfo(taskInfoPlant);
            taskPlant.calculateDueDate();
            taskPlantRepository.save(taskPlant);
        }
    }
}
