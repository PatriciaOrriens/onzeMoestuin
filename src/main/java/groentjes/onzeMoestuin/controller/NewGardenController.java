package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.*;
import groentjes.onzeMoestuin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Patricia Orriens-Spuij and Eric van Dalen
 * Controller for view in which user can create a new garden, or update his/her garden
 */
@Controller
public class NewGardenController {

    private final static int START = 0;
    private final static int MAXIMUM_NUMBER_OF_SHOWN_TASKS = 5;

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskPlantRepository taskPlantRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TaskGardenRepository taskGardenRepository;

//    GetMapping for the overview page of one garden (showGarden.jsp) with its plants, messages and tasks
    @GetMapping("/garden/{gardenId}")
    protected String showGarden(Model model, @PathVariable("gardenId") final Integer gardenId) {

        User user = getUser();

        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if (garden.isPresent()) {
            if(garden.get().isGardenMember(user)) {
                addMessagesToGardenView(garden, user, model);
                addAttributesToShowGardenView(garden.get(), model);
                return "showGarden";
            }
        }
        return "redirect:/";
    }

//    Get- and PostMapping for creating a new garden.
    @GetMapping("/garden/add")
    protected String showGardenForm(Model model) {

        Garden garden = new Garden();
        model.addAttribute("garden", garden);
        return "newGarden";
    }

    @PostMapping({"/garden/add"})
    protected String saveOrUpdateGarden(@Valid Garden garden, Errors errors) {

        if (errors.hasErrors()) {
            return "newGarden";
        } else {
            // Retrieve complete User object from database to be able to add member to garden
            User user = getUser();
            garden.setUser(user);
            garden.addGardenMember(user);
            gardenRepository.save(garden);

            return "redirect:/garden/" + garden.getGardenId();
        }
    }

    // retrieve the first five tasks, which has to be done for one garden, for the view
    private void addAttributesToShowGardenView(Garden garden, Model model) {
        ArrayList<Plant> plants = plantRepository.findAllByGardenAndStartDateIsNotNullAndHarvestDateIsNull(garden);
        ArrayList<Plant> unstartedPlants = plantRepository.findAllByGardenAndStartDateIsNull(garden);
        ArrayList<Task> notCompletedTasks = getListOfNotCompletedTasks(plants, garden);
        ArrayList<Task> tasksToBeShown = new ArrayList<>();
        for (int i = START; i < getNumberOfShownTasks(notCompletedTasks) ; i++) {
            tasksToBeShown.add(notCompletedTasks.get(i));
        }
        model.addAttribute("tasks", tasksToBeShown);
        model.addAttribute("plants", plants);
        model.addAttribute("unstartedPlants", unstartedPlants);
        model.addAttribute("garden", garden);
    }

    private int getNumberOfShownTasks(ArrayList<Task> tasks) {
        if (tasks.size() < MAXIMUM_NUMBER_OF_SHOWN_TASKS) {
            return tasks.size();
        }
        return MAXIMUM_NUMBER_OF_SHOWN_TASKS;
    }

    private ArrayList<Task> getListOfNotCompletedTasks(ArrayList<Plant> plants, Garden garden) {
        ArrayList<Task> notCompletedTasks = new ArrayList<>();
        // load tasks for plants of this garden
        for (Plant plant : plants) {
            ArrayList<TaskPlant> tasksForPlant = taskPlantRepository.findNotCompletedTaskPlant(plant);
            notCompletedTasks.addAll(tasksForPlant);
        }
        ArrayList<TaskGarden> taskGardens = taskGardenRepository.findNotCompletedTaskGarden(garden);
        notCompletedTasks.addAll(taskGardens);
        Collections.sort(notCompletedTasks);
        return notCompletedTasks;
    }

//    This method is used in the GetMapping to show messages connected to one garden
    private void addMessagesToGardenView(Optional<Garden> garden, User user, Model model) {
        // load messages that are connected to this garden
        List<Message> messages = messageRepository.findAllByGardenOrderByDateTimeDesc(garden.get());
        model.addAttribute("messages", messages);
        // initialize a new message
        Message newMessage = new Message();
        newMessage.setSender(user);
        newMessage.setGarden(garden.get());
        model.addAttribute("newMessage", newMessage);
    }

    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByUsername(currentPrincipalName).orElseThrow(() -> new UsernameNotFoundException(currentPrincipalName));
    }
}
