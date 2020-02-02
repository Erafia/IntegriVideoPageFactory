package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Calculator;

import static org.testng.Assert.assertEquals;

public class TestSubstract {
    Calculator calc;

    @DataProvider(parallel = true)
    public Object[] getTestNums() {

        return new Object[][]{
                {12, 4},
                {-10, -5},
                {-11, -5},
                {-12, -5},
                {-13, -5},
                {-14, -5},
                {-15, -5},
                {-16, -5},
                {-17, -5},
                {-18, -5},
                {41, -23},
                {42, -23},
                {43, -23},
                {44, -23},
                {45, -23},
                {46, -23},
                {47, -23},
                {48, -23}
        };
    }

    @BeforeTest
    public void createCalcClassObject() {
        calc = new Calculator();
    }

    @Test(groups = "smoke", dataProvider = "getTestNums",threadPoolSize = 2, invocationCount = 5)
    public void testSubstract(int a, int b) {
        int actual = calc.substraction(a, b);
        assertEquals(actual, (a - b), "Метод вычитания работает некорректно");
    }

    @Test(groups = "smoke", singleThreaded = true)
    public void testSubstractThree() {
        int actual = calc.substraction(5, 10, -15);
        assertEquals(actual, (5 - 10 - (-15)), "Метод вычитания работает некорректно");
    }

}

