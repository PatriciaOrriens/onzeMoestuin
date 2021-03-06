package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.model.TaskDescription;
import groentjes.onzeMoestuin.model.TaskPlantInfo;
import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import groentjes.onzeMoestuin.repository.TaskPlantInfoRepository;
import groentjes.onzeMoestuin.repository.TaskDescriptionRepository;
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
    private TaskDescriptionRepository taskDescriptionRepository;

    @GetMapping("plantinfo/{plantInfoId}/task/add")
    @Secured("ROLE_ADMIN")
    public String addTaskPlantInfo(@PathVariable("plantInfoId") Integer plantInfoId, Model model) {

        Optional<PlantInformation> plantInformation = plantInformationRepository.findById(plantInfoId);
        List<TaskDescription> allTaskDescriptions = taskDescriptionRepository.findAll();

        if (plantInformation.isPresent() && !allTaskDescriptions.isEmpty()) {
            model.addAttribute("plantInfo", plantInformation.get());
            model.addAttribute("taskPlantInfo", new TaskPlantInfo());
            model.addAttribute("allTasks", allTaskDescriptions);
            return "addTaskToPlantInformation";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/plantinfo/{plantInfoId}/task/add")
    @Secured("ROLE_ADMIN")
    public String saveTaskPlantInfo (@RequestParam("taskId") Integer taskId,
                                        @ModelAttribute("taskPlantInfo") TaskPlantInfo taskPlantInfo,
                                        @PathVariable("plantInfoId") final Integer plantInfoId) {

        Optional<PlantInformation> plantInformation = plantInformationRepository.findById(plantInfoId);
        Optional<TaskDescription> task = taskDescriptionRepository.findById(taskId);

        if (plantInformation.isPresent() && task.isPresent()) {
            taskPlantInfo.setPlantInformation(plantInformation.get());
            taskPlantInfo.setTaskDescription(task.get());
            taskPlantInfoRepository.save(taskPlantInfo);
            return "redirect:/plantinfo/tasks/" + plantInfoId;
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/plantinfo/{plantInfoId}/task/update/{taskPlantInfoId}")
    @Secured("ROLE_ADMIN")
    public String showUpdateTaskPlantInfo(@PathVariable("plantInfoId") final Integer plantInfoId,
                                          @PathVariable("taskPlantInfoId") final Integer taskPlantInfoId, Model model) {

        Optional<PlantInformation> plantInformation = plantInformationRepository.findById(plantInfoId);
        Optional<TaskPlantInfo> foundTaskPlantInfo = taskPlantInfoRepository.findById(taskPlantInfoId);
        List<TaskDescription> allTaskDescriptions = taskDescriptionRepository.findAll();

        if (foundTaskPlantInfo.isPresent()) {
            model.addAttribute("taskPlantInfo", foundTaskPlantInfo.get());
            model.addAttribute("plantInformation", plantInformation.get());
            model.addAttribute("allTasks", allTaskDescriptions);
            return "changeTaskToPlantInformation";
        } else {
            return "redirect:/adminManagePlantInformation";
        }
    }

    @PostMapping("/plantinfo/{plantInfoId}/task/update/{taskPlantInfoId}")
    @Secured("ROLE_ADMIN")
    public String updateTaskPlantInfo(@RequestParam("taskId") Integer taskId,
                                      @PathVariable("plantInfoId") final Integer plantInfoId,
                                      @PathVariable("taskPlantInfoId") final Integer taskPlantInfoId,
                                      @ModelAttribute("taskPlantInfo") TaskPlantInfo taskPlantInfo,
                                      BindingResult result) {

        Optional<PlantInformation> plantInformation = plantInformationRepository.findById(plantInfoId);
        Optional<TaskDescription> task = taskDescriptionRepository.findById(taskId);

        if (result.hasErrors()) {
            return "redirect:/plantinfo/task/update";
        } else {
            taskPlantInfo.setTaskDescription(task.get());
            taskPlantInfo.setPlantInformation(plantInformation.get());
            taskPlantInfo.setTaskPlantInfoId(taskPlantInfoId);
            taskPlantInfoRepository.save(taskPlantInfo);
            return "redirect:/plantinfo/tasks/" + plantInfoId;
        }
    }

    @GetMapping("/plantinfo/task/delete/{taskPlantInfoId}")
    @Secured("ROLE_ADMIN")
    public String deleteTaskPlantInfo(@ModelAttribute("taskPlantInfoId") Integer taskPlantInfoId) {

        Optional<TaskPlantInfo> taskPlantInfo = taskPlantInfoRepository.findById(taskPlantInfoId);
        taskPlantInfo.ifPresent(information -> taskPlantInfoRepository.delete(information));
        return "redirect:/adminManagePlantInformation";
    }

}
