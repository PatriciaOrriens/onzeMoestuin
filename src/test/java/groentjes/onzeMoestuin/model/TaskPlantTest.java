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

    private static long numberOfMillisecond = 1579267590507L;
    private static int numberOfDays = 18;

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
        String expectedString = "2020-02-04";

        // Activate
        taskPlant.calculateDueDate();
        String foundString = taskPlant.getDueDate();

        // Assert
        Assertions.assertEquals(expectedString, foundString);
    }
}