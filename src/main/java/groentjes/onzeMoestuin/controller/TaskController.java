package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.*;
import groentjes.onzeMoestuin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

/**
 * @author Eric van Dalen
 * Controller class for managing tasks
 */
@Controller
public class TaskController {

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private TaskPlantRepository taskPlantRepository;

    @Autowired
    private TaskGardenRepository taskGardenRepository;

    @GetMapping("/userTaskOverview/{gardenId}")
    public String findAllTasks(Model model, @PathVariable("gardenId") final Integer gardenId,
                          @AuthenticationPrincipal User user) {

        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if (garden.isPresent()) {
            if (garden.get().isGardenMember(user)) {
                ArrayList<Plant> plants = plantRepository.findAllByGarden(garden.get());
                // load tasks for plants of this garden
                ArrayList<Task> tasks = new ArrayList<>();
                for (Plant plant : plants) {
                    ArrayList<TaskPlant> tasksForPlant = taskPlantRepository.findAllByPlant(plant);
                    tasks.addAll(tasksForPlant);
                }
                ArrayList<TaskGarden> taskGardens = taskGardenRepository.findAllByGarden(garden.get());
                tasks.addAll(taskGardens);
                Collections.sort(tasks);
                model.addAttribute("tasks", tasks);
                return "showTaskOverview";
            }
        }
        return "manageUsers";
    }
}
