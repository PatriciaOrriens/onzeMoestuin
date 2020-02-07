package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.*;
import groentjes.onzeMoestuin.repository.PlantRepository;
import groentjes.onzeMoestuin.repository.TaskGardenRepository;
import groentjes.onzeMoestuin.repository.TaskPlantRepository;
import groentjes.onzeMoestuin.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private PlantRepository plantRepository;

    @Autowired
    private TaskPlantRepository taskPlantRepository;

    @Autowired
    private TaskGardenRepository taskGardenRepository;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/user/task/completed/{taskId}")
    public String processCompletedTaskPlant(@PathVariable("taskId") final Integer taskId,
                                            @AuthenticationPrincipal User user) {

        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            if (task.get() instanceof TaskPlant) {
                TaskPlant taskPlant = (TaskPlant) task.get();
                Plant plant = taskPlant.getPlant();
                completeDataTaskPlant(taskPlant, plant, user);
            } else if (task.get() instanceof TaskGarden) {
                completeDataTaskGarden((TaskGarden) task.get(), user);
            }
            return getRedirect(task.get());
        }
        return "redirect:/";
    }

    private String getRedirect(Task task) {

        if (task instanceof TaskPlant) {
            Plant plant = ((TaskPlant) task).getPlant();
            Garden garden = plant.getGarden();
            return "redirect:/garden/" + garden.getGardenId();
        } else if (task instanceof TaskGarden) {
            return "redirect:/garden/" + ((TaskGarden) task).getGarden().getGardenId();
        }
        return "redirect:/";
    }

    // store the date that the garden task is performed and the person who did it, if task is not yet performed
    private void completeDataTaskGarden(TaskGarden taskGarden, User user) {

        if (taskGarden.getCompletedDate() == null && taskGarden.getUser() == null) {
            taskGarden.setCompletedDate(taskGarden.getStringFromDate(new Date()));
            taskGarden.setUser(user);
            taskGardenRepository.save(taskGarden);
        }
    }

    // store the date that the plant task is performed and the person who did it, if task is not yet performed,
    // and check if it is a repetitive task.
    private void completeDataTaskPlant(TaskPlant taskPlant, Plant plant, User user) {

        TaskPlantInfo taskPlantInfo = taskPlant.getTaskPlantInfo();
        if (taskPlant.getCompletedDate() == null && taskPlant.getUser() == null) {
            taskPlant.setCompletedDate(taskPlant.getStringFromDate(new Date()));
            taskPlant.setUser(user);
            taskPlantRepository.save(taskPlant);
            if(taskPlant.getTaskPlantInfo().isRepetitiveTask()) {
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
