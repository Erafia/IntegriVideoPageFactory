package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    WebDriverWait wait;
    WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
    }

    public void click(WebElement element){
        element.click();
    }

    public abstract void isPageOpened();

    public void waitForVisibiltyOfElement(By locator, String message, int timeout){
        try{
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        catch (TimeoutException e){
            throw new TimeoutException(message);
        }
    }
}
