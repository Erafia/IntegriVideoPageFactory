package tests;

import org.testng.annotations.*;
import pages.Calculator;

import static org.testng.Assert.assertEquals;

public class TestSum {

    Calculator calc;
    int totalTestsExecuted = 0;

    @BeforeTest
    public void createCalcClassObject(){
        calc = new Calculator();
        System.out.println("Test started");
    }

    @BeforeClass
    public void startClass(){
        System.out.println("Class starts here");
    }

    @AfterClass
    public void endClass(){
        System.out.println("Have you seen this class?");
    }

    @AfterTest
    public void printAnnounceTestEnd(){
        System.out.println("Test ended");
    }

    @BeforeSuite
    public void printAnnounceSuiteStart(){
        System.out.println("Suite comes here");
    }

    @AfterSuite
    public void printAnnounceSuiteEnd(){
        System.out.println("Suite ends here");
    }

    @BeforeMethod(/*onlyForGroups = "smoke"*/) //странно, но не отрабатывает ни с одной Before-аннотацией
    public void printAnnounceSmokeMethod(){
        System.out.println("Тест принадлежит smoke-скоупу");
        totalTestsExecuted++;
    }

    @AfterMethod
    public void testCount(){
        System.out.println("Количество выполненных тестов: " + totalTestsExecuted);
    }

    @BeforeGroups (groups = "smoke")
    public void announceGroup(){
        System.out.println("Сим объявляю группу запущенной");
    }

    @AfterGroups (groups = "smoke")
    public void announceGroupEnd(){
        System.out.println("Сим объявляю группу законченной");
    }

    @Test (enabled = false, groups = "smoke", description = "Check method can count sum when all arguments are positive")
    public void testSummingPositiveNumbers(){
        int actual = calc.sum(12,45,66);
        assertEquals(actual, (12 + 45 + 66), "Метод суммирования работает некорректно для положительных чисел");
    }

    @Test (groups = "smoke", description = "Check method can count sum when all arguments are negative")
    public void testSummingNegativeNumbers(){
        int actual = calc.sum(-2,-55,-100);
        assertEquals(actual, (-2 + -55 + -100), "Метод суммирования работает некорректно для отрицательных чисел");
    }

    @Test (dependsOnGroups = "smoke", groups = {"another"} )
    public void testSummingNegAndPosNums(){
        int actual = calc.sum(42,-42,-100);
        assertEquals(actual, (42 + -42 + -100), "Метод суммирования работает некорректно для negative + positive чисел");
    }
}
