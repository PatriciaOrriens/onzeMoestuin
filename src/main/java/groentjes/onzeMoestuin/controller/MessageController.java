package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Message;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.MessageRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


/**
 * @Author Patricia Orriens-Spuij
 * controller to list all messages for one garden and to create and save a new message
 */
@Controller
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GardenRepository gardenRepository;

    @GetMapping("/garden/{gardenId}/newMessage")
    public String getNewMessageForm (Model model, @PathVariable("gardenId") final Integer gardenId,
                                     @AuthenticationPrincipal User user) {

        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if (garden.isPresent() && garden.get().isGardenMember(user)) {
                Message message = new Message();
                message.setSender(user);
                model.addAttribute(garden);
                return "newMessageForm";
        } else {
            return "redirect:/garden/" + gardenId;
        }
    }






}
