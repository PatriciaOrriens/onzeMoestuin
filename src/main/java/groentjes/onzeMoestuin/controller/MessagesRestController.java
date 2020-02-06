package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Message;
import groentjes.onzeMoestuin.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api", produces="application/json")
public class MessagesRestController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/garden/{id}/recentMessages")
    public List<Message> recentMessages() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return messageRepository.findAll(page).getContent();
    }

}
