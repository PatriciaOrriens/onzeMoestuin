package groentjes.onzeMoestuin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groentjes.onzeMoestuin.model.Plant;
import groentjes.onzeMoestuin.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class AjaxRestController {

    @Autowired
    private PlantRepository plantRepository;

    @GetMapping("/getPlant/{id}")
    public ResponseEntity<Plant> getPlant(@PathVariable("id") Integer plantId) {
        Optional<Plant> plant = plantRepository.findById(plantId);

        if (plant.isPresent()) {
            return new ResponseEntity<>(plant.get(), HttpStatus.OK);
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
    public void startPlant(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Plant plantStart = mapper.readValue(json, Plant.class);

        Plant plant = plantRepository.getOne(plantStart.getPlantId());
        plant.setWidth(plantStart.getWidth());
        plant.setHeight(plantStart.getHeight());
        plant.setxCoordinate(plantStart.getxCoordinate());
        plant.setyCoordinate(plantStart.getyCoordinate());
        plant.setStartDate(new Date());
        plantRepository.save(plant);
    }
}
