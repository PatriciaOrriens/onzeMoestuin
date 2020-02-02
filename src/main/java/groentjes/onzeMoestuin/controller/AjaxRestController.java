package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Plant;
import groentjes.onzeMoestuin.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
