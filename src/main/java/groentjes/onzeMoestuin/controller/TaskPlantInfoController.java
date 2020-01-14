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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

/**
 * @author Patricia Orriens-Spuij
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
    public String addNewTaskPlantInfo(@PathVariable("plantInfoId") Integer plantInfoId, Model model) {
        Optional<PlantInformation> plantInformation = plantInformationRepository.findById(plantInfoId);
        List<Task> allTasks = taskRepository.findAll();

        if (plantInformation.isPresent() && !allTasks.isEmpty()) {
            model.addAttribute("plantInfo", plantInformation.get());
            model.addAttribute("newTask", new TaskPlantInfo());
            model.addAttribute("allTasks", allTasks);
            return "addTaskToPlantInformation";
        } else {
            return "redirect:/";
        }
    }

//    @PostMapping("/plantinfo/tasks/{plantInfoId}")
//    @Secured("ROLE_ADMIN")
//    public String saveNewTaskPlantInfo (@ModelAttribute()TaskPlantInfo taskPlantInfo, BindingResult result) {
//        if(result.hasErrors()) {
//            return "/plantinfo/tasks/{plantInfoId}";
//        } else {
//            taskPlantInfoRepository.save(taskPlantInfo);
//            return "redirect:/plantinfo/tasks/{plantInfoId}";
//        }
//    }


}
