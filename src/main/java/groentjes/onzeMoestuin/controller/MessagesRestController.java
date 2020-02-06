package groentjes.onzeMoestuin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Message;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api", produces="application/json")
public class MessagesRestController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @GetMapping("/garden/{id}/messages/{page}")
    public List<Message> recentMessages(@PathVariable("id") Integer gardenId,
                                        @PathVariable("page") Integer pageNumber) {

        Optional<Garden> searchedGarden = gardenRepository.findById(gardenId);

        if (searchedGarden.isPresent()) {
            Garden garden = searchedGarden.get();
            PageRequest page = PageRequest.of(
                    pageNumber, 4, Sort.by("dateTime").descending());
            return messageRepository.findAllByGarden(garden, page);
        }
        return null;
    }


    @PostMapping("/garden/{id}/messages/add")
    public ResponseEntity<Object> postMessage(@RequestBody String json,
                                              @PathVariable("id") Integer gardenId,
                                              @AuthenticationPrincipal User user) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Message newMessage = mapper.readValue(json, Message.class);

        Optional<Garden> garden = gardenRepository.findById(gardenId);
        if (garden.isPresent() && garden.get().isGardenMember(user)) {
            newMessage.setSender(user);
            newMessage.setDateTime(LocalDateTime.now());
            newMessage.setGarden(garden.get());
            messageRepository.save(newMessage);
        }
        return new ResponseEntity<Object>(newMessage, HttpStatus.OK);
    }
}
