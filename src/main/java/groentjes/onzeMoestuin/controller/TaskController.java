package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Task;
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

import java.util.Optional;

/**
 * @author Patricia Orriens-Spuij
 * Controller for generic tasks (for administrator)
 */
@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // admin gets list of all task names and loads form for adding new task
    @GetMapping("/adminManageTasks")
    @Secured("ROLE_ADMIN")
    public String manageTasks(Model model) {
        model.addAttribute("allTasks", taskRepository.findAll());
        Task newTask = new Task();
        model.addAttribute("newTask", newTask);
        return "adminManageTasks";
    }

    // admin creates new (generic) tasks
    @PostMapping("/adminManageTasks")
    @Secured("ROLE_ADMIN")
    public String saveNewTask(@ModelAttribute()Task task, BindingResult result) {
        if (result.hasErrors()){
            return "adminManageTasks";
        } else {
            taskRepository.save(task);
            return "redirect:/adminManageTasks";
        }
    }

    // admin wishes to change a (generic) task
    @GetMapping("/task/update/{taskId}")
    @Secured("ROLE_ADMIN")
    protected String showUpdateTask(@PathVariable("taskId") final Integer taskId, Model model){
        Optional<Task> foundTask = taskRepository.findById(taskId);
        if (foundTask.isPresent()) {
            model.addAttribute("task", foundTask.get());
            return "adminChangeTask";
        }
        return "redirect:/adminManageTasks";
    }

    @PostMapping("/task/update/{taskId}")
    @Secured("ROLE_ADMIN")
    protected String updatePlantInfo(@PathVariable("taskId") final Integer taskId,
                                     @ModelAttribute("task") Task task,
                                     BindingResult result) {
        if (result.hasErrors()){
            return "redirect:/task/update";
        } else {
            task.setTaskId(taskId);
            taskRepository.save(task);
            return "redirect:/adminManageTasks";
        }
    }

    // admin deletes a task from task list
    //TODO Ask user for confirmation
    @GetMapping("/task/delete/{taskId}")
    @Secured("ROLE_ADMIN")
    public String deleteTask(@ModelAttribute("taskId") Integer taskId, BindingResult result) {
        Optional<Task> task = taskRepository.findById(taskId);
        task.ifPresent(information -> taskRepository.delete(information));
        return "redirect:/adminManageTasks";
    }

}
