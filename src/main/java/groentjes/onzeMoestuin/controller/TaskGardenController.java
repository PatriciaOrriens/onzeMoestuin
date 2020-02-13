package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.*;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.TaskGardenRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Eric van Dalen
 * Controller class for managing tasks, which have to be performed in the whole garden
 */
@Controller
public class TaskGardenController {

    private static final String DATE_ERROR = "Vervaldatum moet het patroon dd-mm-jjjj hebben";
    private static final String EMPTY = "";

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private TaskGardenRepository taskGardenRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/garden/{gardenId}/addTaskGarden")
    protected String addTaskGarden(Model model, @PathVariable("gardenId") final Integer gardenId) {

        User user = getUser();

        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if (garden.isPresent()) {
            if(garden.get().isGardenMember(user)) {
                TaskGardenBuilder taskGardenBuilder = new TaskGardenBuilder();
                taskGardenBuilder.createNewTaskGarden();
                model.addAttribute("taskGarden", taskGardenBuilder.getTaskGarden());
                return "addTaskGarden";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/garden/{gardenId}/addTaskGarden")
    protected String storeTaskGarden(@PathVariable("gardenId") final Integer gardenId,
                                     @Valid TaskGarden taskGarden, Errors error,
                                     @ModelAttribute("remark") String remark,
                                     Model model) {

        User user = getUser();

        model.addAttribute("remark", EMPTY);
        if(error.hasErrors()) {
            return "addTaskGarden";
        } else if (!taskGarden.isDateString(taskGarden.getDueDate())) {
            model.addAttribute("remark", DATE_ERROR);
            return "addTaskGarden";
        }
        return storeTaskGardenAndReturn(gardenId, user, taskGarden);
    }

    private String storeTaskGardenAndReturn(int gardenId, User user, TaskGarden taskGarden) {
        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if(garden.isPresent()) {
            if(garden.get().isGardenMember(user)) {
                TaskGardenBuilder taskGardenBuilder = new TaskGardenBuilder();
                taskGardenBuilder.createNewTaskGarden();
                taskGardenBuilder.buildDueDate(taskGarden.getDueDate()).buildLinkToGarden(garden.get());
                taskGardenBuilder.buildTaskGardenName(taskGarden.getTaskGardenName());
                taskGardenRepository.save(taskGardenBuilder.getTaskGarden());
                return "redirect:/garden/" + gardenId;
            }
        }
        return "redirect:/";
    }

    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByUsername(currentPrincipalName).orElseThrow(() -> new UsernameNotFoundException(currentPrincipalName));
    }
}
