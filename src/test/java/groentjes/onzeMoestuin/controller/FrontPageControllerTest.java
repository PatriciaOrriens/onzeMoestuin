package groentjes.onzeMoestuin.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Eric van Dalen
 * Test class for the frontpage
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class FrontPageControllerTest {

    @Autowired
    private FrontPageController frontPageController;

    @Test
    void testGetFrontPage() {
        // Arrange
        String expectedString = "frontPage";

        //Activate
        String actualString = frontPageController.getFrontPage();

        //Assert
        Assertions.assertEquals(expectedString, actualString);
    }
}