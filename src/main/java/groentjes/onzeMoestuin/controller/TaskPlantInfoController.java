package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.model.Task;
import groentjes.onzeMoestuin.model.TaskPlantInfo;
import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import groentjes.onzeMoestuin.repository.TaskPlantInfoRepository;
import groentjes.onzeMoestuin.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Patricia Orriens-Spuij and Wim Kruizinga
 * Controller for tasks for certain plantinformation (views for administrator)
 */
@Controller
public class TaskPlantInfoController {

    @Autowired
    private PlantInformationRepository plantInformationRepository;

    @Autowired
    private TaskPlantInfoRepository taskPlantInfoRepository;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("plantinfo/{plantInfoId}/task/add")
    @Secured("ROLE_ADMIN")
    public String addTaskPlantInfo(@PathVariable("plantInfoId") Integer plantInfoId, Model model) {
        Optional<PlantInformation> plantInformation = plantInformationRepository.findById(plantInfoId);
        List<Task> allTasks = taskRepository.findAll();

        if (plantInformation.isPresent() && ! allTasks.isEmpty()) {
            model.addAttribute("plantInfo", plantInformation.get());
            model.addAttribute("taskPlantInfo", new TaskPlantInfo());
            model.addAttribute("allTasks", allTasks);
            return "addTaskToPlantInformation";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/plantinfo/{plantInfoId}/task/add")
    @Secured("ROLE_ADMIN")
    public String saveTaskPlantInfo (@RequestParam("taskId") Integer taskId,
                                        @ModelAttribute("taskPlantInfo") TaskPlantInfo taskPlantInfo, BindingResult result,
                                        @PathVariable("plantInfoId") final Integer plantInfoId) {

        Optional<PlantInformation> plantInformation = plantInformationRepository.findById(plantInfoId);
        Optional<Task> task = taskRepository.findById(taskId);

        if (plantInformation.isPresent() && task.isPresent()) {
            taskPlantInfo.setPlantInformation(plantInformation.get());
            taskPlantInfo.setTask(task.get());
            taskPlantInfoRepository.save(taskPlantInfo);
            System.out.println("geslaagd!");
            return "redirect:/plantinfo/tasks/" + plantInfoId;
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/plantinfo/task/update/{taskPlantInfoId}")
    @Secured("ROLE_ADMIN")
    public String showUpdateTaskPlantInfo(@PathVariable("taskPlantInfoId") Integer taskPlantInfoId, Model model) {
        Optional<TaskPlantInfo> foundTaskPlantInfo = taskPlantInfoRepository.findById(taskPlantInfoId);
        if (foundTaskPlantInfo.isPresent()) {
            model.addAttribute("taskPlantInfo", foundTaskPlantInfo.get());
            return "adminManagePlantInformation";
        } else {
            return "redirect:/adminManagePlantInformation";
        }
    }
/*
    @PostMapping("/plantinfo/task/update/{taskPlantInfoId}")
    @Secured("ROLE_ADMIN")
    public String updateTaskPlantInfo(@PathVariable("taskPlantInfoId") final Integer taskPlantInfoId,
                                      @ModelAttribute("taskPlantInfo") TaskPlantInfo taskPlantInfo,
                                      BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/plantinfo/task/update";
        } else {
            taskPlantInfo.setTaskPlantInfoId(taskPlantInfoId);
            taskPlantInfoRepository.save(taskPlantInfo);
            return "redirect:/adminManagePlantInformation";
        }
    }
*/
    @GetMapping("/plantinfo/task/delete/{taskPlantInfoId}")
    @Secured("ROLE_ADMIN")
    public String deleteTaskPlantInfo(@ModelAttribute("taskPlantInfoId") Integer taskPlantInfoId,
                                      BindingResult result) {
        Optional<TaskPlantInfo> taskPlantInfo = taskPlantInfoRepository.findById(taskPlantInfoId);
        taskPlantInfo.ifPresent(information -> taskPlantInfoRepository.delete(information));
        return "redirect:/adminManagePlantInformation";
    }

}
