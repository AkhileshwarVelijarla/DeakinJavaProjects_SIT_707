package sit707_week6;

import org.junit.Assert;
import org.junit.Test;

public class WeatherAndMathUtilsTest {

    @Test
    public void testStudentIdentity() {
        String studentId = "224763306";
        Assert.assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Akhilesh";
        Assert.assertNotNull("Student name is null", studentName);
    }

    @Test
    public void testTrueNumberIsEven() {
        Assert.assertTrue(WeatherAndMathUtils.isEven(8));
    }

    @Test
    public void testFalseNumberIsEven() {
        Assert.assertFalse(WeatherAndMathUtils.isEven(7));
    }

    @Test
    public void testCancelWeatherAdviceForDangerousWind() {
        Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(70.1, 0.0));
    }

    @Test
    public void testCancelWeatherAdviceForDangerousRain() {
        Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(0.0, 6.1));
    }

    @Test
    public void testCancelWeatherAdviceForCombinedConcerningConditions() {
        Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(46.0, 4.1));
    }

    @Test
    public void testWarnWeatherAdviceForConcerningWindOnly() {
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(46.0, 2.0));
    }

    @Test
    public void testWarnWeatherAdviceForConcerningRainOnly() {
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(10.0, 4.5));
    }

    @Test
    public void testAllClearWeatherAdvice() {
        Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(20.0, 2.0));
    }

    @Test
    public void testWeatherAdviceInvalidInput() {
        Assert.assertThrows(IllegalArgumentException.class,
                () -> WeatherAndMathUtils.weatherAdvice(-1.0, 2.0));
    }

    @Test
    public void testBoundaryWindExactly70() {
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(70.0, 0.0));
    }

    @Test
    public void testBoundaryRainExactly6() {
        Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(0.0, 6.0));
    }

    @Test
    public void testBoundaryWindExactly45() {
        Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(45.0, 2.0));
    }

    @Test
    public void testBoundaryRainExactly4() {
        Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(10.0, 4.0));
    }

    @Test
    public void testBothZero() {
        Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(0.0, 0.0));
    }

    @Test
    public void testPrimeWhenOne() {
        Assert.assertTrue(WeatherAndMathUtils.isPrime(1));
    }

    @Test
    public void testPrimeWhenEvenNumber() {
        Assert.assertFalse(WeatherAndMathUtils.isPrime(8));
    }

    @Test
    public void testPrimeWhenOddNumber() {
        Assert.assertTrue(WeatherAndMathUtils.isPrime(7));
    }
}