package sit707_week5;

import org.junit.*;
import static org.junit.Assert.*;

public class WeatherControllerTest {

    private static WeatherController wController;
    private static double[] temperatures;
    private static int nHours;

    @BeforeClass
    public static void setUpBeforeClass() {
        // Arrange (run once)
        wController = WeatherController.getInstance();
        nHours = wController.getTotalHours();
        temperatures = new double[nHours];

        for (int i = 0; i < nHours; i++) {
            temperatures[i] = wController.getTemperatureForHour(i + 1);
        }
    }

    @AfterClass
    public static void tearDownAfterClass() {
        if (wController != null) {
            wController.close();
        }
    }

    @Test
    public void testStudentIdentity() {
        String studentId = "224763306";
        assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Akhilesh";
        assertNotNull("Student name is null", studentName);
    }

    @Test
    public void testTemperatureMin() {
        // Arrange
        double expectedMin = temperatures[0];

        // Act
        for (int i = 1; i < nHours; i++) {
            if (temperatures[i] < expectedMin) {
                expectedMin = temperatures[i];
            }
        }

        // Assert
        assertEquals(expectedMin, wController.getTemperatureMinFromCache(), 0.0001);
    }

    @Test
    public void testTemperatureMax() {
        // Arrange
        double expectedMax = temperatures[0];

        // Act
        for (int i = 1; i < nHours; i++) {
            if (temperatures[i] > expectedMax) {
                expectedMax = temperatures[i];
            }
        }

        // Assert
        assertEquals(expectedMax, wController.getTemperatureMaxFromCache(), 0.0001);
    }

    @Test
    public void testTemperatureAverage() {
        // Arrange
        double sum = 0;

        // Act
        for (int i = 0; i < nHours; i++) {
            sum += temperatures[i];
        }

        double expectedAverage = sum / nHours;

        // Assert
        assertEquals(expectedAverage, wController.getTemperatureAverageFromCache(), 0.0001);
    }

    @Test
    public void testTemperaturePersist() {
        // Leave unchanged for 5.1P
    }
}