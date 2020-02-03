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
import java.util.Optional;

/**
 * @author Eric van Dalen and Gjalt Wybenga
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
        if (garden.isPresent()) {
            if(garden.get().isGardenMember(user)) {
                Plant plant = new Plant();
                plant.setGarden(garden.get());
                model.addAttribute("plant", plant);
                model.addAttribute("garden", garden.get());
                model.addAttribute("allPlantInformation", plantInformationRepository.findAll());
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

    @PostMapping("/garden/{gardenId}/addPlant")
    public String addPlantToGarden(@RequestParam("plantInfoId") Integer plantInfoId, @ModelAttribute("plant") Plant plant,
                                   BindingResult result, @PathVariable("gardenId") final Integer gardenId,
                                   @AuthenticationPrincipal User user) {

        Optional<PlantInformation> plantInfo  = plantInformationRepository.findById(plantInfoId);
        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if (plantInfo.isPresent() && garden.isPresent()) {
            if(garden.get().isGardenMember(user)) {
                savePlantAndTaskPlant(plantInfo, garden, plant);
                return "redirect:/garden/" + gardenId;
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
