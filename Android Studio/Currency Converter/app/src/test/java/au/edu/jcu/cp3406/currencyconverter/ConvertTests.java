package au.edu.jcu.cp3406.currencyconverter;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConvertTests {
    // test conversion methods for each currency
    // test rounding off method to specific decimal places

    double input = 20.86;

    @Test
    public void testConvertUSD() {
        double result = Convert.round(input * 0.73, 5);
        assertEquals(15.2278, result, 0);
    }

    @Test
    public void testConvertGBP() {
        double result = Convert.round(input * 0.55, 4);
        assertEquals(11.473, result, 0);
    }

    @Test
    public void testConvertEUR() {
        double result = Convert.round(input * 0.65, 3);
        assertEquals(13.559, result, 0);
    }

    @Test
    public void testConvertAUD() {
        double result = Convert.round(input * 1.03, 2);
        assertEquals(21.49, result, 0);
    }

    @Test
    public void testConvertJPY() {
        double result = Convert.round(input * 82.57, 1);
        assertEquals(1722.4, result, 0);
    }

    @Test
    public void testConvertCHF() {
        double result = Convert.round(input * 0.67, 0);
        assertEquals(14, result, 0);
    }


}