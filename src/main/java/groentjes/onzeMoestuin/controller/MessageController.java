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

    @GetMapping("/garden/{gardenId}/newMessage")
    public String getNewMessageForm (Model model, @PathVariable("gardenId") final Integer gardenId,
                                     @AuthenticationPrincipal User user) {

        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if (garden.isPresent() && garden.get().isGardenMember(user)) {
                Message message = new Message();
                message.setSender(user);
                message.setGarden(garden);
                return "newMessageForm";
        } else {
            return "redirect:/garden/" + gardenId;
        }
    }

    @PostMapping("/garden/{gardenId}/newMessage")
    public String sendMessage (@ModelAttribute("message") Message message, BindingResult result,
                               @PathVariable("gardenId") final Integer gardenId,
                               @AuthenticationPrincipal User user) {

        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if (result.hasErrors()) {
            // rather show modal
            System.out.println("message was niet in orde");
            return "redirect:/";
        } else {
            if (garden.isPresent() && garden.get().isGardenMember(user)) {
                message.setSender(user);
                message.setGarden(garden);
                message.setDateTime(LocalDateTime.now());
                messageRepository.save(message);
                System.out.println("message is opgeslagen");
            }
        }
        return "redirect:/garden/" + gardenId;
    }

}
