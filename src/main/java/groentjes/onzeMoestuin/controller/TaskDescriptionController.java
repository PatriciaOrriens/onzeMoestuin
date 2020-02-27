package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.TaskDescription;
import groentjes.onzeMoestuin.repository.TaskDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Patricia Orriens-Spuij and Eric van Dalen
 * Controller for generic tasks (for administrator)
 */
@Controller
public class TaskDescriptionController {

    @Autowired
    private TaskDescriptionRepository taskDescriptionRepository;

    // admin gets list of all task names and loads form for adding new task
    @GetMapping("/adminManageTasks")
    @Secured("ROLE_ADMIN")
    public String manageTaskDescriptions(Model model) {
        model.addAttribute("allTasks", taskDescriptionRepository.findAll());
        TaskDescription taskDescription = new TaskDescription();
        model.addAttribute("taskDescription", taskDescription);
        return "adminManageTasks";
    }

    // admin creates new task descriptions
    @PostMapping("/adminManageTasks")
    @Secured("ROLE_ADMIN")
    public String saveNewTaskDescription(@Valid TaskDescription taskDescription, Errors errors, Model model) {

        if (errors.hasErrors()){
            model.addAttribute("allTasks", taskDescriptionRepository.findAll());
            return "adminManageTasks";
        } else {
            taskDescriptionRepository.save(taskDescription);
            return "redirect:/adminManageTasks";
        }
    }

    // admin wishes to change a task description
    @GetMapping("/task/update/{taskId}")
    @Secured("ROLE_ADMIN")
    protected String showTaskDescriptionForUpdate(@PathVariable("taskId") Integer taskDescriptionId, Model model){
        Optional<TaskDescription> foundTask = taskDescriptionRepository.findById(taskDescriptionId);
        if (foundTask.isPresent()) {
            model.addAttribute("taskDescription", foundTask.get());
            return "adminChangeTask";
        }
        return "redirect:/adminManageTasks";
    }

    @PostMapping("/task/update/{taskId}")
    @Secured("ROLE_ADMIN")
    protected String updateTaskDescription(@PathVariable("taskId") Integer taskId,
                                           @Valid TaskDescription taskDescription, Errors errors) {

        if (errors.hasErrors()){
            return "adminChangeTask";
        } else {
            taskDescription.setTaskDescriptionId(taskId);
            taskDescriptionRepository.save(taskDescription);
            return "redirect:/adminManageTasks";
        }
    }

    // admin deletes a task from task list
    @GetMapping("/task/delete/{taskId}")
    @Secured("ROLE_ADMIN")
    public String deleteTaskDescription(@PathVariable("taskId") Integer taskId) {
        Optional<TaskDescription> task = taskDescriptionRepository.findById(taskId);
        task.ifPresent(information -> taskDescriptionRepository.delete(information));
        return "redirect:/adminManageTasks";
    }

}
