package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.*;
import groentjes.onzeMoestuin.repository.PlantRepository;
import groentjes.onzeMoestuin.repository.TaskPlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

/**
 * @author Eric van Dalen
 * Controller class for managing plant tasks
 */
@Controller
public class TaskPlantController {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private TaskPlantRepository taskPlantRepository;

    @GetMapping("/user/taskPlant/completed/{taskPlantId}")
    public String completeTaskPlant(@PathVariable("taskPlantId") final Integer taskPlantId, @AuthenticationPrincipal User user) {
        Optional<TaskPlant> taskPlant = taskPlantRepository.findById(taskPlantId);
        if (taskPlant.isPresent()) {
            Plant plant = taskPlant.get().getPlant();
            Garden garden = plant.getGarden();
            TaskPlantInfo taskPlantInfo = taskPlant.get().getTaskPlantInfo();
            if (taskPlant.get().getCompletedDate() == null && taskPlant.get().getUser() == null) {
                taskPlant.get().setCompletedDate(taskPlant.get().getCurrentDate());
                taskPlant.get().setUser(user);
                taskPlantRepository.save(taskPlant.get());
                if(taskPlant.get().getTaskPlantInfo().isRepetitiveTask()) {
                    TaskPlant newTaskPlant = new TaskPlant();
                    newTaskPlant.setPlant(plant);
                    newTaskPlant.setTaskPlantInfo(taskPlantInfo);
                    newTaskPlant.calculateDueDate();
                    taskPlantRepository.save(newTaskPlant);
                }
            }
            return "redirect:/garden/" + garden.getGardenId();
        }
        return "redirect:/login";
    }
}
