package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.*;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.TaskGardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author Eric van Dalen
 * Controller class for managing tasks, which have to be performed in the whole garden
 */
@Controller
public class TaskGardenController {

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private TaskGardenRepository taskGardenRepository;

    @GetMapping("/garden/{gardenId}/addTaskGarden")
    protected String addTaskGarden(Model model, @PathVariable("gardenId") final Integer gardenId,
                                   @AuthenticationPrincipal User user) {

        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if (garden.isPresent()) {
            if(garden.get().isGardenMember(user)) {
                TaskGardenBuilder taskGardenBuilder = new TaskGardenBuilder();
                taskGardenBuilder.createNewTaskGarden();;
                model.addAttribute("taskGarden", taskGardenBuilder.getTaskGarden());
                return "addTaskGarden";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/garden/{gardenId}/addTaskGarden")
    protected String storeTaskGarden(@PathVariable("gardenId") final Integer gardenId,
                                     @ModelAttribute("taskGarden") TaskGarden taskGarden,
                                     @AuthenticationPrincipal User user ) {

        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if(garden.isPresent()) {
            if(garden.get().isGardenMember(user)) {
                TaskGardenBuilder taskGardenBuilder = new TaskGardenBuilder();
                taskGardenBuilder.createNewTaskGarden();
                taskGardenBuilder.buildDueDate(taskGarden.getDueDate()).buildLinkToGarden(garden.get());
                taskGardenBuilder.buildTaskGardenName(taskGarden.getTaskGardenName());
                System.out.println(taskGardenBuilder.getTaskGarden().getTaskGardenName());
                taskGardenRepository.save(taskGardenBuilder.getTaskGarden());
                return "redirect:/garden/" + gardenId;
            }
        }
        return "redirect:/";
    }
}
