package groentjes.onzeMoestuin.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

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

    @Test
    void testGetGardenId() {
        // Arrange
        Integer expectedGardenId = TWO;
        Garden newGarden = new Garden();
        newGarden.setGardenId(TWO);

        // Activate
        Integer foundGardenId = newGarden.getGardenId();

        // Assert
        Assertions.assertEquals(expectedGardenId, foundGardenId);
    }

    @Test
    void testGetGardenName() {
        // Arrange
        String expectedGardenName = NAMEGARDEN;
        Garden newGarden = new Garden();
        newGarden.setGardenName(NAMEGARDEN);

        // Activate
        String foundGardenName = newGarden.getGardenName();

        // Assert
        Assertions.assertEquals(expectedGardenName, foundGardenName);
    }

    @Test
    void testGetLength() {
        // Arrange
        Integer expectedLength = ONE;
        Garden newGarden = new Garden();
        newGarden.setLength(ONE);

        // Activate
        Integer foundLength = newGarden.getLength();

        // Assert
        Assertions.assertEquals(expectedLength, foundLength);
    }

    @Test
    void testGetWidth() {
        // Arrange
        Integer expectedWidth = TWO;
        Garden newGarden = new Garden();
        newGarden.setWidth(TWO);

        // Activate
        Integer foundWidth = newGarden.getWidth();

        // Assert
        Assertions.assertEquals(expectedWidth, foundWidth);
    }

    @Test
    void testGetUser() {
        // Arrange
        User expectedUser = gardenOwner;
        Garden newGarden = new Garden();
        newGarden.setUser(gardenOwner);

        // Activate
        User foundUser = newGarden.getUser();

        // Assert
        Assertions.assertEquals(expectedUser, foundUser);
    }

    @Test
    void testGetGardenMembers() {
        // Arrange
        int expectedSize = TWO;
        Garden newGarden = new Garden();
        Set<User> expectedGardenMembers = new HashSet<>();;
        expectedGardenMembers .add(gardenOwner);
        expectedGardenMembers .add(user);
        newGarden.setGardenMembers(expectedGardenMembers);

         // Activate
        Set<User> foundGardenMembers = newGarden.getGardenMembers();

        // Assert
        Assertions.assertEquals(expectedSize, foundGardenMembers.size());
        Assertions.assertEquals(expectedGardenMembers, foundGardenMembers);
    }
}