package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.*;
import groentjes.onzeMoestuin.repository.PlantRepository;
import groentjes.onzeMoestuin.repository.TaskPlantRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Optional;

/**
 * @author Eric van Dalen
 * Controller class for managing plant tasks
 */
@Controller
public class TaskPlantController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private TaskPlantRepository taskPlantRepository;

    @GetMapping("/user/taskPlant/completed/{taskPlantId}")
    public String processCompletedTaskPlant(@PathVariable("taskPlantId") final Integer taskPlantId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user =
                userRepository.findByUsername(currentPrincipalName).orElseThrow(() -> new UsernameNotFoundException(currentPrincipalName));

        Optional<TaskPlant> taskPlant = taskPlantRepository.findById(taskPlantId);
        if (taskPlant.isPresent()) {
            Plant plant = taskPlant.get().getPlant();
            Garden garden = plant.getGarden();
            completeDataTaskPlant(taskPlant, plant, user);
            return "redirect:/garden/" + garden.getGardenId();
        }
        return "redirect:/";
    }

    private void completeDataTaskPlant(Optional<TaskPlant> taskPlant, Plant plant, User user) {

        TaskPlantInfo taskPlantInfo = taskPlant.get().getTaskPlantInfo();
        if (taskPlant.get().getCompletedDate() == null && taskPlant.get().getUser() == null) {
            taskPlant.get().setCompletedDate(taskPlant.get().getStringFromDate(new Date()));
            taskPlant.get().setUser(user);
            taskPlantRepository.save(taskPlant.get());
            if(taskPlant.get().getTaskPlantInfo().isRepetitiveTask()) {
                createAndSaveNewTaskPlant(plant, taskPlantInfo);
            }
        }
    }

    private void createAndSaveNewTaskPlant(Plant plant, TaskPlantInfo taskPlantInfo) {
        TaskPlant newTaskPlant = new TaskPlant();
        newTaskPlant.setPlant(plant);
        newTaskPlant.setTaskPlantInfo(taskPlantInfo);
        newTaskPlant.calculateDueDate();
        taskPlantRepository.save(newTaskPlant);
    }
}
