package pt.jfmartins.calculator;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcMethodsTest {

    @InjectMocks
    private CalcMethods calcMethods;

    @Test
    void testWhenSumOfToDoublesReturnsValue(){
        double a = 2;
        double b = 1.5;

        double result = CalcMethods.sum(a,b);
        assertEquals(3.5, result);
    }

    @Test
    void testWhenSumWithZeroReturnsValue(){
        double a = 2;
        double b = 0;

        double result = CalcMethods.sum(a,b);
        assertEquals(2, result);
    }

    @Test
    void testWhenSumWithNegativeNumberReturnsValue(){
        double a = 2;
        double b = -5;

        double result = CalcMethods.sum(a,b);
        assertEquals(-3, result);
    }

    @Test
    void testWhenSumWithTwoNegativeNumbersReturnsValue(){
        double a = -3;
        double b = -5;

        double result = CalcMethods.sum(a,b);
        assertEquals(-8, result);
    }

    @Test
    void testWhenSubtractOfToDoublesReturnsValue(){
        double a = 2;
        double b = 1.5;

        double result = CalcMethods.subtract(a,b);
        assertEquals(0.5, result);
    }

    @Test
    void testWhenSubtractWithZeroReturnsValue(){
        double a = 2;
        double b = 0;

        double result = CalcMethods.subtract(a,b);
        assertEquals(2, result);
    }

    @Test
    void testWhenSubtractWithNegativeNumberReturnsValue(){
        double a = 2;
        double b = -5;

        double result = CalcMethods.subtract(a,b);
        assertEquals(7, result);
    }

    @Test
    void testWhenSubtractWithTwoNegativeNumbersReturnsValue(){
        double a = -3;
        double b = -5;

        double result = CalcMethods.subtract(a,b);
        assertEquals(2, result);
    }

    @Test
    void testWhenMultiplytractOfToDoublesReturnsValue(){
        double a = 2;
        double b = 1.5;

        double result = CalcMethods.multiply(a,b);
        assertEquals(3, result);
    }

    @Test
    void testWhenMultiplyWithZeroReturnsValue(){
        double a = 2;
        double b = 0;

        double result = CalcMethods.multiply(a,b);
        assertEquals(0, result);
    }

    @Test
    void testWhenMultiplyWithNegativeNumberReturnsValue(){
        double a = 2;
        double b = -5;

        double result = CalcMethods.multiply(a,b);
        assertEquals(-10, result);
    }

    @Test
    void testWhenMultiplyWithTwoNegativeNumbersReturnsValue(){
        double a = -3;
        double b = -5;

        double result = CalcMethods.multiply(a,b);
        assertEquals(15, result);
    }

    @Test
    void testWhenDivideOfToDoublesReturnsValue(){
        double a = 3;
        double b = 1.5;

        double result = CalcMethods.divide(a,b);
        assertEquals(2, result);
    }

    @Test
    void testWhenDivideWithZeroReturnsValue(){
        double a = 0;
        double b = 2;

        double result = CalcMethods.divide(a,b);
        assertEquals(0, result);
    }

    @Test
    void testWhenDivideWithNegativeNumberReturnsValue(){
        double a = -10;
        double b = 2;

        double result = CalcMethods.divide(a,b);
        assertEquals(-5, result);
    }

    @Test
    void testWhenDivideWithTwoNegativeNumbersReturnsValue(){
        double a = -6;
        double b = -3;

        double result = CalcMethods.divide(a,b);
        assertEquals(2, result);
    }
}
