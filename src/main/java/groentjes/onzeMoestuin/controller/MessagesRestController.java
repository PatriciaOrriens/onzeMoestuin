package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Message;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api", produces="application/json")
public class MessagesRestController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @GetMapping("/garden/{id}/recentMessages")
    public List<Message> recentMessages(@PathVariable("id") Integer gardenId) {

        Optional<Garden> searchedGarden = gardenRepository.findById(gardenId);

        if (searchedGarden.isPresent()) {
            Garden garden = searchedGarden.get();
            PageRequest page = PageRequest.of(
                    0, 1, Sort.by("dateTime").descending());
            return messageRepository.findAllByGarden(garden, page);
        }
        return null;

    }

}
