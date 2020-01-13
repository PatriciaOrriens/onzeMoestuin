package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.model.TaskPlantInfo;
import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import groentjes.onzeMoestuin.repository.TaskPlantInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * @author Patricia Orriens-Spuij
 * Controller for tasks for certain plantinformation (views for administrator)
 */
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
        Optional<PlantInformation> plantInformation = plantInformationRepository.findById(plantInfoId);
        if(plantInformation.isPresent()) {
            model.addAttribute("allTasksPlantInfo", taskPlantInfoRepository.findAllByPlantInformation(plantInformation.get()));
        }
        return "adminManageTasksPlantInfo";
    }

    /*
    // voor deze view zijn alleen de taken nodig die NOG NIET aangemaakt zijn voor deze plantInfo
    @GetMapping("/plantinfo/tasks/{plantInfoId}/new")
    @Secured("ROLE_ADMIN")
    public String getAllTasks

    return "adminCreateTaskPlantInfo";
    */

}
