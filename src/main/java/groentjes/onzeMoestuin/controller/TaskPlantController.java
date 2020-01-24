package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Plant;
import groentjes.onzeMoestuin.model.TaskPlant;
import groentjes.onzeMoestuin.model.User;
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
            if (taskPlant.get().getCompletedDate() == null && taskPlant.get().getUser() == null) {
                taskPlant.get().setCompletedDate(taskPlant.get().getCurrentDate());
                taskPlant.get().setUser(user);
                taskPlantRepository.save(taskPlant.get());
            }
            Plant plant = taskPlant.get().getPlant();
            Garden garden = plant.getGarden();
            return "redirect:/garden/" + garden.getGardenId();
        }
        return "redirect:/login";
    }
}
