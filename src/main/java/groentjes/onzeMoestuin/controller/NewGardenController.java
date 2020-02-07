package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.*;
import groentjes.onzeMoestuin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
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

    @GetMapping("/garden/{gardenId}")
    protected String showGarden(Model model, @PathVariable("gardenId") final Integer gardenId,
                                @AuthenticationPrincipal User user) {

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

    @GetMapping("/garden/add")
    protected String showGardenForm(Model model, @AuthenticationPrincipal User user) {
        Garden garden = new Garden();
        garden.setUser(user);
        model.addAttribute("garden", garden);

        return "newGarden";
    }

    @PostMapping({"/garden/add"})
    protected String saveOrUpdateGarden(@ModelAttribute("garden") Garden garden, BindingResult result,
                                        @AuthenticationPrincipal User user) {

        if (result.hasErrors()) {
            return "newGarden";
        } else {
            // Retrieve complete User object from database to be able to add member to garden
            User owner = userRepository.getOne(user.getUserId());
            garden.addGardenMember(owner);
            garden = gardenRepository.save(garden);

            return "redirect:/garden/" + garden.getGardenId();
        }
    }

    // retrieve all tasks for one garden for view
    private void addAttributesToShowGardenView(Garden garden, Model model) {
        ArrayList<Plant> plants = plantRepository.findAllByGarden(garden);
        // load tasks for plants of this garden
        ArrayList<TaskPlant> taskPlants = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Plant plant : plants) {
            ArrayList<TaskPlant> tasksForPlant = taskPlantRepository.findAllByPlant(plant);
            tasks.addAll(tasksForPlant);
        }
        ArrayList<TaskGarden> taskGardens = taskGardenRepository.findAllByGarden(garden);
        tasks.addAll(taskGardens);
        Collections.sort(tasks);
        model.addAttribute("tasks", tasks);
        model.addAttribute("plants", plants);
        model.addAttribute("garden", garden);
    }

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

}
