package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Message;
import groentjes.onzeMoestuin.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Wim Kruizinga
 * DataJpaTest of the MessageRepository
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MessageRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MessageRepository messageRepository;

    @Mock
    private Garden garden;

    @Mock
    private User user;

    @Before
    public void setup() {
        // Initialize necessary dependencies
        user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        garden = new Garden();
        garden.setGardenName("testgarden");
        garden.setLength(1);
        garden.setWidth(1);
        entityManager.persist(user);
        entityManager.persist(garden);
    }


    @Test
    public void should_find_no_messages_if_empty() {
        Iterable<Message> messages = messageRepository.findAll();
        assertThat(messages).isEmpty();
    }


    @Test
    public void should_save_a_message() {
        Message message = new Message();
        message.setMessageBody("test message");
        message.setGarden(garden);
        message.setSender(user);
        message.setDateTime(LocalDateTime.now());
        Message savedMessage = messageRepository.save(message);
        assertThat(savedMessage).hasFieldOrPropertyWithValue("messageBody", "test message");
    }


}
