package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(name = "email")
    WebElement emailField;
    @FindBy(name = "password")
    WebElement passwordField;
    @FindBy(id = "login-form")
    WebElement loginForm;

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public void openPage() {
        driver.get("https://dev.integrivideo.com/login");
        isPageOpened();
        PageFactory.initElements(driver, this); //LoginPage.this ?
    }

    public void isPageOpened() {
        waitForVisibiltyOfElement(By.id("login-form"),"Страница логина не открыта", 10);
    }

    public ProjectsPage login(User user) {
        emailField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        loginForm.submit();
        ProjectsPage projects = new ProjectsPage(driver);
        projects.isPageOpened();
        return projects;
    }
}
