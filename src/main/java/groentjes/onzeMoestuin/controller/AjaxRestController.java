package groentjes.onzeMoestuin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groentjes.onzeMoestuin.model.*;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.PlantRepository;
import groentjes.onzeMoestuin.repository.TaskPlantInfoRepository;
import groentjes.onzeMoestuin.repository.TaskPlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class AjaxRestController {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private TaskPlantInfoRepository taskPlantInfoRepository;

    @Autowired
    private TaskPlantRepository taskPlantRepository;

    @GetMapping("/getPlant/{id}")
    public ResponseEntity<Plant> getPlant(@PathVariable("id") Integer plantId) {
        Optional<Plant> plant = plantRepository.findById(plantId);

        if (plant.isPresent()) {
            return new ResponseEntity<>(plant.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/garden/{id}/getPlannedPlants")
    public ResponseEntity<List<Plant>> getPlannedPlants(@PathVariable("id") Integer gardenId) {
        Optional<Garden> searchedGarden = gardenRepository.findById(gardenId);

        if (searchedGarden.isPresent()) {
            Garden garden = searchedGarden.get();
            return new ResponseEntity<>(plantRepository.findAllByGardenAndStartDateIsNull(garden), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @PostMapping("/plant/resize")
    public void resizePlant(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Plant updatedPlant = mapper.readValue(json, Plant.class);

        Plant plant = plantRepository.getOne(updatedPlant.getPlantId());
        plant.setWidth(updatedPlant.getWidth());
        plant.setHeight(updatedPlant.getHeight());
        plantRepository.save(plant);
    }


    @PostMapping("/plant/move")
    public void movePlant(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Plant updatedPlant = mapper.readValue(json, Plant.class);

        Plant plant = plantRepository.getOne(updatedPlant.getPlantId());
        plant.setxCoordinate(updatedPlant.getxCoordinate());
        plant.setyCoordinate(updatedPlant.getyCoordinate());
        plantRepository.save(plant);
    }


    @PostMapping("/plant/start")
    public ResponseEntity<Plant> startPlant(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Plant plantStart = mapper.readValue(json, Plant.class);

        Plant plant = plantRepository.getOne(plantStart.getPlantId());
        plant.setWidth(plantStart.getWidth());
        plant.setHeight(plantStart.getHeight());
        plant.setxCoordinate(plantStart.getxCoordinate());
        plant.setyCoordinate(plantStart.getyCoordinate());
        plant.setStartDate(new Date());
        generatePlantTasks(plant);
        return new ResponseEntity<>(plantRepository.save(plant), HttpStatus.CREATED);
    }


    // Method to generate initial tasks for plant when it is put into garden
    private void generatePlantTasks(Plant plant) {
        ArrayList<TaskPlantInfo> taskPlantInfos = taskPlantInfoRepository.findAllByPlantInformation(plant.getPlantInformation());
        for (TaskPlantInfo task : taskPlantInfos) {
            TaskPlant taskPlant = new TaskPlant();
            taskPlant.setPlant(plant);
            taskPlant.setTaskPlantInfo(task);
            taskPlant.calculateDueDate();
            taskPlantRepository.save(taskPlant);
        }
    }
}
