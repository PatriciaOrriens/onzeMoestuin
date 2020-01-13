package groentjes.onzeMoestuin.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Eric van Dalen
 * tests for methods in Plant model
 */
class PlantTest {

    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final String NAME = "testgebruiker1";
    private static final String PASSWORD = "testwachtwoord1";
    private static final String SECONDNAME = "testgebruiker2";
    private static final String SECONDPASSWORD = "testwachtwoord2";
    private static final String NAMEGARDEN = "testtuin1";

    private User plantOwner = new User();
    private Garden garden = new Garden();
    private User user = new User();
    private Plant plant = new Plant();

    @BeforeEach
    public void setUp() {
        plantOwner.setUserId(ONE);
        plantOwner.setUsername(NAME);
        plantOwner.setPassword(PASSWORD);
        garden.setGardenName(NAMEGARDEN);
        garden.setUser(plantOwner);
        garden.addGardenMember(plantOwner);
        user.setUserId(TWO);
        user.setUsername(SECONDNAME);
        user.setPassword(SECONDPASSWORD);
        plant.setPlantId(ONE);
        plant.setGarden(garden);
    }

    @Test
    void testIsOwnerOfPlant() {
        // Arrange
        boolean expectedResultPlantOwner = true;
        boolean expectedResultUser = false;

        // Activate
        boolean foundResultPlantOwner = plant.isOwnerOfPlant(plantOwner);
        boolean foundResultUser = plant.isOwnerOfPlant(user);

        // Assert
        Assertions.assertEquals(expectedResultPlantOwner, foundResultPlantOwner);
        Assertions.assertEquals(expectedResultUser, foundResultUser);
    }
}