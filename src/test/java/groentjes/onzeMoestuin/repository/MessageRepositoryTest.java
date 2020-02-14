package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Message;
import groentjes.onzeMoestuin.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    private Garden garden;
    private User user;

    LocalDateTime time;

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

        time = LocalDateTime.now();
    }


    @Test
    public void should_find_no_messages_if_empty() {
        Iterable<Message> messages = messageRepository.findAll();
        assertThat(messages).isEmpty();
    }


    @Test
    public void should_save_a_message() {
        Message message = new Message(user, garden, "test message", time);
        Message savedMessage = messageRepository.save(message);
        assertThat(savedMessage).hasFieldOrPropertyWithValue("messageBody", "test message");
    }


    @Test
    public void should_get_messages_by_garden() {
        Message message1 = new Message(user, garden, "test message 1", time);
        Message message2 = new Message(user, garden, "test message 2", time);
        Message message3 = new Message(user, garden, "test message 3", time);
        entityManager.persist(message1);
        entityManager.persist(message2);
        entityManager.persist(message3);

        // test getting first page of all 3 messages
        PageRequest page = PageRequest.of(0, 3, Sort.by("dateTime").descending());
        Iterable<Message> pageOfThree = messageRepository.findAllByGarden(garden, page);
        assertThat(pageOfThree).hasSize(3).contains(message1, message2, message3);
    }
}
