package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Message;
import groentjes.onzeMoestuin.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MessageRepositoryIntegrationTest {
    List<Message> testMessages = new ArrayList<>();

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void returnTestByGardenOrderByDateDesc() {
        // Arrange
        User testUser1 = new User();
        testUser1.setUserId(1);

        Message testMessage = new Message();
        Garden testGarden1 = new Garden();
        testGarden1.setGardenId(1);
        testMessage.setMessageId(1);
        testMessage.setTitle("testMessage");
        testMessage.setMessageBody("testMessageBody");
        testMessage.setDateTime(LocalDateTime.now());
        testMessage.setGarden(testGarden1);
        testMessage.setSender(testUser1);
        messageRepository.save(testMessage);

        Message testMessage2 = new Message();
        Garden testGarden2 = new Garden();
        testGarden2.setGardenId(2);
        testMessage2.setMessageId(2);
        testMessage2.setTitle("testMessage");
        testMessage2.setMessageBody("testMessageBody");
        testMessage2.setDateTime(LocalDateTime.now());
        testMessage2.setGarden(testGarden2);
        testMessage2.setSender(testUser1);
        messageRepository.save(testMessage2);

        // Activate
        List<Message> results = messageRepository.findAllByGardenOrderByDateTimeDesc(testMessage.getGarden());

        // Assert
        

    }
}
