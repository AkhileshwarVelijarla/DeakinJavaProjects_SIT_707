package sit707_week2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

    @Before
    public void setup() {
        System.out.println("Running basic student identity tests...");
    }

    @Test
    public void testStudentIdentity() {
        String studentId = "224763306";
        Assert.assertNotNull("Student ID is null", studentId);
        Assert.assertEquals("224763306", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Akhileshwar Reddy Velijarla";
        Assert.assertNotNull("Student name is null", studentName);
        Assert.assertTrue(studentName.length() > 0);
    }

    @Test
    public void testAssertTrue() {
        Assert.assertTrue(true);
    }

    @Test
    public void testAssertFalse() {
        Assert.assertFalse(false);
    }
}