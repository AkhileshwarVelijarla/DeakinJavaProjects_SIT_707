package sit707_week6;

import org.junit.Assert;
import org.junit.Test;

public class LoopUtilsTest {

    @Test
    public void testSumUpToZero() {
        Assert.assertEquals(0, LoopUtils.sumUpTo(0));
    }

    @Test
    public void testSumUpToOne() {
        Assert.assertEquals(1, LoopUtils.sumUpTo(1));
    }

    @Test
    public void testSumUpToFive() {
        Assert.assertEquals(15, LoopUtils.sumUpTo(5));
    }

    @Test
    public void testSumUpToTen() {
        Assert.assertEquals(55, LoopUtils.sumUpTo(10));
    }

    @Test
    public void testCountEvenNumbersUpToZero() {
        Assert.assertEquals(0, LoopUtils.countEvenNumbersUpTo(0));
    }

    @Test
    public void testCountEvenNumbersUpToOne() {
        Assert.assertEquals(0, LoopUtils.countEvenNumbersUpTo(1));
    }

    @Test
    public void testCountEvenNumbersUpToTwo() {
        Assert.assertEquals(1, LoopUtils.countEvenNumbersUpTo(2));
    }

    @Test
    public void testCountEvenNumbersUpToSix() {
        Assert.assertEquals(3, LoopUtils.countEvenNumbersUpTo(6));
    }

    @Test
    public void testCountEvenNumbersUpToTen() {
        Assert.assertEquals(5, LoopUtils.countEvenNumbersUpTo(10));
    }
}