package main.function;

import org.junit.Test;
import static org.junit.Assert.*;

public class CosineSeriesCalculatorTest {

    @Test
    public void testZero() {
        double result = CosineSeriesCalculator.calculateCosine(0, 10);
        assertEquals(1.0, result, 1e-5);
    }

    @Test
    public void testPiByTwo() {
        double result = CosineSeriesCalculator.calculateCosine(Math.PI / 2, 10);
        assertEquals(0.0, result, 1e-5);
    }

    @Test
    public void testPi() {
        double result = CosineSeriesCalculator.calculateCosine(Math.PI, 10);
        assertEquals(-1.0, result, 1e-5);
    }

    @Test
    public void testSmallValue() {
        double result = CosineSeriesCalculator.calculateCosine(0.1, 10);
        double expected = Math.cos(0.1);
        assertEquals(expected, result, 1e-5);
    }

    @Test
    public void testLargeValue() {
        double result = CosineSeriesCalculator.calculateCosine(Math.PI * 2, 20);//10 Не хватает точности
        assertEquals(1.0, result, 1e-5);
    }

    @Test
    public void testNegativeValue() {
        double result = CosineSeriesCalculator.calculateCosine(-Math.PI / 2, 10);
        assertEquals(0.0, result, 1e-5);
    }

    @Test
    public void testThreeByTwo() {
        double result = CosineSeriesCalculator.calculateCosine(Math.PI * 3 / 2, 10);
        assertEquals(0.0, result, 1e-3);
    }
    @Test
    public void testThreeByTwoBetween() {
        double result = CosineSeriesCalculator.calculateCosine(Math.PI * 5 / 4, 10);
        assertEquals(-0.707, result, 1e-3);
    }


    @Test
    public void testDifferentTerms() {
        double x = Math.PI / 4;
        double result5 = CosineSeriesCalculator.calculateCosine(x, 5);
        double result10 = CosineSeriesCalculator.calculateCosine(x, 10);
        double result15 = CosineSeriesCalculator.calculateCosine(x, 15);

        assertEquals(result10, result15, 1e-10);
        // Проверяем, что увеличение количества членов улучшает точность
        assertTrue(Math.abs(result10 - Math.cos(x)) < Math.abs(result5 - Math.cos(x)));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1.0, CosineSeriesCalculator.calculateCosine(0, 1), 1e-10);
        assertEquals(1.0, CosineSeriesCalculator.calculateCosine(0, 2), 1e-10);
    }
}