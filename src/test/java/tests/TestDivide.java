package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Calculator;

import static org.testng.Assert.assertEquals;

public class TestDivide {
    Calculator calc;

    @BeforeTest
    public void createCalcClassObject(){
        calc = new Calculator();
    }

    @Test (groups = "smoke", timeOut = 1)
    public void divideEtImperaPositive(){
        int actual = calc.division(10,2);
        assertEquals(actual, (10/2), "Метод деления работает некорректно для положительных чисел");
    }

    @Test (expectedExceptions = ArithmeticException.class)
    public void testDivideByZero(){
        int actual = calc.division(10,0);

    }
}

