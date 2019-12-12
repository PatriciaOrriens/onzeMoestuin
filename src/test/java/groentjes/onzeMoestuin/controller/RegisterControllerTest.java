package groentjes.onzeMoestuin.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Eric van Dalen
 * De test klasse voor de SignInController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class RegisterControllerTest {

    @Autowired
    private RegisterController registerController;

    @Test
    void testRegisterUser() {
        // Arrange
        String verwachteString = "redirect:/login";

        // Activate
        String gevondenString = registerController.registerUser("testUser", "testPassword");

        // Assert
        Assertions.assertEquals(verwachteString, gevondenString);
    }
}