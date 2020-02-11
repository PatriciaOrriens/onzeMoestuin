package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Message;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.MessageRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @PostMapping("/garden/{gardenId}/newMessage")
    public String sendMessage (@ModelAttribute("message") Message message, BindingResult result,
                               @PathVariable("gardenId") final Integer gardenId,
                               @AuthenticationPrincipal User user) {

        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if (result.hasErrors()) {
            return "redirect:/";
        } else {
            if (garden.isPresent() && garden.get().isGardenMember(user)) {
                message.setSender(user);
                message.setGarden(garden.get());
                message.setDateTime(LocalDateTime.now());
                messageRepository.save(message);
            }
        }
        return "redirect:/garden/" + gardenId;
    }

}