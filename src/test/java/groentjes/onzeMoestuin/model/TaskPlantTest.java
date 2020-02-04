package groentjes.onzeMoestuin.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

/**
 * @author Eric van Dalen
 * tests for methods in TaskPlant model
 */
class TaskPlantTest {

    private static final long numberOfMillisecond = 1579267590507L;
    private static final int numberOfDays = 18;
    private static final String FIRST_DATE = "25-01-1980";
    private static final String SECOND_DATE = "25-01-2020";
    private static final String THIRD_DATE = "25-02-2020";
    private static final String FOURTH_DATE = "26-02-2020";

    private TaskPlant taskPlant = new TaskPlant();
    private Plant plant = new Plant();
    private TaskPlantInfo taskPlantInfo = new TaskPlantInfo();
    private Date datum = new Date();

    @BeforeEach
    public void setUp() {
        datum.setTime(numberOfMillisecond);
        plant.setStartDate(datum);
        taskPlantInfo.setDaysAfterStart(numberOfDays);
        taskPlant.setPlant(plant);
        taskPlant.setTaskPlantInfo(taskPlantInfo);
    }

    @Test
    void testCalculateDueDate() {
        // Arrange
        String expectedString = "04-02-2020";

        // Activate
        taskPlant.calculateDueDate();
        String foundString = taskPlant.getDueDate();

        // Assert
        Assertions.assertEquals(expectedString, foundString);
    }

    @Test
    void testGetStringFromDate() {
        // Arrange
        String expectedString = "17-01-2020";

        // Activate
        String foundString = taskPlant.getStringFromDate(datum);

        //Assert
        Assertions.assertEquals(expectedString, foundString);
    }

    @Test
    void testCompareTo() {
        int expectedResult1 = -1;
        int expectedResult2 = 1;
        int expectedResult3 = -1;
        int expectedResult4 = 0;

        // Activate
        int foundResult1 = FIRST_DATE.compareTo(SECOND_DATE);
        int foundResult2 = THIRD_DATE.compareTo(SECOND_DATE);
        int foundResult3 = THIRD_DATE.compareTo(FOURTH_DATE);
        int foundResult4 = FOURTH_DATE.compareTo(FOURTH_DATE);

        // Assert
        Assertions.assertEquals(expectedResult1, foundResult1);
        Assertions.assertEquals(expectedResult2, foundResult2);
        Assertions.assertEquals(expectedResult3, foundResult3);
        Assertions.assertEquals(expectedResult4, foundResult4);
    }
}