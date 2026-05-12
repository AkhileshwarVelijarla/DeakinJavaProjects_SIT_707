package sit707_week5;

import org.junit.*;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherControllerTest {

    private static WeatherController wController;
    private static double[] temperatures;
    private static int nHours;

    @BeforeClass
    public static void setUpBeforeClass() {
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
        assertEquals("224763306", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Akhilesh";
        assertNotNull("Student name is null", studentName);
        assertEquals("Akhilesh", studentName);
    }

    @Test
    public void testTemperatureMin() {
        double expectedMin = temperatures[0];

        for (int i = 1; i < nHours; i++) {
            if (temperatures[i] < expectedMin) {
                expectedMin = temperatures[i];
            }
        }

        assertEquals(expectedMin, wController.getTemperatureMinFromCache(), 0.0001);
    }

    @Test
    public void testTemperatureMax() {
        double expectedMax = temperatures[0];

        for (int i = 1; i < nHours; i++) {
            if (temperatures[i] > expectedMax) {
                expectedMax = temperatures[i];
            }
        }

        assertEquals(expectedMax, wController.getTemperatureMaxFromCache(), 0.0001);
    }

    @Test
    public void testTemperatureAverage() {
        double sum = 0;

        for (int i = 0; i < nHours; i++) {
            sum += temperatures[i];
        }

        double expectedAverage = sum / nHours;

        assertEquals(expectedAverage, wController.getTemperatureAverageFromCache(), 0.0001);
    }

    @Test
    public void testTemperaturePersist() {
        // Arrange
        int hour = 1;
        double temperature = 25.5;

        Date fixedDate = new Date(0);
        SimpleDateFormat sdf = new SimpleDateFormat("H:m:s");
        String expectedSavedTime = sdf.format(fixedDate);

        wController.setDateSupplier(() -> fixedDate);

        // Act
        String actualSavedTime = wController.persistTemperature(hour, temperature);

        // Assert
        assertEquals(expectedSavedTime, actualSavedTime);
    }
}