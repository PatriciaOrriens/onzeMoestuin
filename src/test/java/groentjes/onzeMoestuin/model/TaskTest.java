package groentjes.onzeMoestuin.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Eric van Dalen
 * tests for methods in Task model
 */
class TaskTest {

    private static final String CORRECT_DATE = "25-01-1980";
    private static final String FIRST_INCORRECT_DATE = "bb-bb-bbbb";
    private static final String SECOND_INCORRECT_DATE = "33-12-2020";
    private static final String THIRD_INCORRECT_DATE = "10-13-2020";

    private Task task = new Task();

    @Test
    void testIsDateString() {
        // Arrange
        boolean expectedResult1 = true;
        boolean expectedResult2 = false;
        boolean expectedResult3 = false;
        boolean expectedResult4 = false;

        // Activate
        boolean foundResult1 = task.isDateString(CORRECT_DATE);
        boolean foundResult2 = task.isDateString(FIRST_INCORRECT_DATE);
        boolean foundResult3 = task.isDateString(SECOND_INCORRECT_DATE);
        boolean foundResult4 = task.isDateString(THIRD_INCORRECT_DATE);

        // Assert
        Assertions.assertEquals(expectedResult1, foundResult1);
        Assertions.assertEquals(expectedResult2, foundResult2);
        Assertions.assertEquals(expectedResult3, foundResult3);
        Assertions.assertEquals(expectedResult4, foundResult4);
    }
}