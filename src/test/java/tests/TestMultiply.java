package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Calculator;
import utils.RetryAnalyzer;

import static org.testng.Assert.assertEquals;

public class TestMultiply {
    Calculator calc;

    @BeforeTest
    public void createCalcClassObject(){
        calc = new Calculator();
    }

    @Test (groups = "another", retryAnalyzer = RetryAnalyzer.class)
    public void martyMcMultiply(){
        int actual = calc.multiplication(10,2);
        assertEquals(actual, (10 * 3), "Метод умножения работает некорректно для положительных чисел");
        //туть для наглядности некорректный expected result. It`s alive!
    }

}

