package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Eric van Dalen
 * DataJpaTest of the UserRepository
 */
@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest {

    private static final String USERNAME = "gebruikersnaam";
    private static final String OTHER_USERNAME = "gebruikersnaam2";
    private static final String EMAIL = "gebruiker@email.com";
    private static final String OTHER_EMAIL = "gebruiker2@email.com";

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByUsername() {
        // Arrange
        User testUser = new User();
        testUser.setUsername(USERNAME);
        userRepository.save(testUser);

        // Activate
        Optional<User> foundUser = userRepository.findByUsername(USERNAME);
        Optional<User> noFoundUser = userRepository.findByUsername(OTHER_USERNAME);

        // Assert
        assertTrue(foundUser.isPresent());
        Assertions.assertEquals(USERNAME, foundUser.get().getUsername());
        assertFalse(noFoundUser.isPresent());
    }

    @Test
    void testFindByEmail() {
        // Arrange
        User testUser = new User();
        testUser.setEmail(EMAIL);
        userRepository.save(testUser);

        // Activate
        Optional<User> foundUser = userRepository.findByEmail(EMAIL);
        Optional<User> noFoundUser = userRepository.findByEmail(OTHER_EMAIL);

        // Assert
        assertTrue(foundUser.isPresent());
        Assertions.assertEquals(EMAIL, foundUser.get().getEmail());
        assertFalse(noFoundUser.isPresent());
    }
}