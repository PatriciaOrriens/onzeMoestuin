package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.model.Task;
import groentjes.onzeMoestuin.model.TaskPlantInfo;
import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import groentjes.onzeMoestuin.repository.TaskPlantInfoRepository;
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

    // admin can make changes to tasks for a plantInformation
    @GetMapping("/plantinfo/tasks/{plantInfoId}")
    @Secured("ROLE_ADMIN")
    public String getTasksForPlantInfo (@PathVariable("plantInfoId") final Integer plantInfoId,
                                        @ModelAttribute("taskPlantInfo") TaskPlantInfo taskPlantInfo,
                                        Model model) {
       Optional<PlantInformation> foundPlantInformation = plantInformationRepository.findById(plantInfoId);
       if(foundPlantInformation.isPresent()) {
           List<TaskPlantInfo> allTasks = taskPlantInfoRepository.findAllByPlantInformation(foundPlantInformation.get());
           for (TaskPlantInfo task : allTasks) {
               System.out.println(task.getTask().getTaskName());
           }
            model.addAttribute("allTasksPlantInfo", taskPlantInfoRepository.findAllByPlantInformation(foundPlantInformation.get()));
        }
        TaskPlantInfo newTask = new TaskPlantInfo();
        model.addAttribute("newTask", newTask);
        return "adminManageTasksPlantInfo";
    }

    @PostMapping("/plantinfo/tasks/{plantInfoId}")
    @Secured("ROLE_ADMIN")
    public String saveNewTaskPlantInfo (@ModelAttribute()TaskPlantInfo taskPlantInfo, BindingResult result) {
        if(result.hasErrors()) {
            return "/plantinfo/tasks/{plantInfoId}";
        } else {
            taskPlantInfoRepository.save(taskPlantInfo);
            return "redirect:/plantinfo/tasks/{plantInfoId}";
        }
    }


}
