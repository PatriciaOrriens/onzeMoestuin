package groentjes.onzeMoestuin.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Eric van Dalen
 * tests for methods in Garden model
 */
class GardenTest {

    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final String NAME = "testgebruiker1";
    private static final String PASSWORD = "testwachtwoord1";
    private static final String SECONDNAME = "testgebruiker2";
    private static final String SECONDPASSWORD = "testwachtwoord2";
    private static final String NAMEGARDEN = "testtuin1";

    private User gardenOwner = new User();
    private Garden garden = new Garden();
    private User user = new User();

    @BeforeEach
    public void setUp() {
        gardenOwner.setUserId(ONE);
        gardenOwner.setUsername(NAME);
        gardenOwner.setPassword(PASSWORD);
        garden.setGardenName(NAMEGARDEN);
        garden.setUser(gardenOwner);
        garden.addGardenMember(gardenOwner);
        user.setUserId(TWO);
        user.setUsername(SECONDNAME);
        user.setPassword(SECONDPASSWORD);
    }

    @Test
    void testIsGardenMember() {
        // Arrange
        boolean expectedResultGardenOwner = true;
        boolean expectedResultUser = false;

        // Activate
        boolean foundResultGardenOwner = garden.isGardenMember(gardenOwner);
        boolean foundResultUser = garden.isGardenMember(user);

        // Assert
        Assertions.assertEquals(expectedResultGardenOwner, foundResultGardenOwner);
        Assertions.assertEquals(expectedResultUser, foundResultUser);
    }
}