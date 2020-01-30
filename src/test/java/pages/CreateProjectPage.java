package pages;
import models.ProjectFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CreateProjectPage extends BasePage {
    By CREATE_PROJECT_BUTTON = By.xpath("//button[text()='Create']");
    By NAME_FIELD = By.name("name");
    By DESCRIPTION_FIELD = By.xpath("//textarea[@name='description']");
    By DOMAINS_FIELD = By.name("domains[]");

    public CreateProjectPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://dev.integrivideo.com/app/projects/new");
        isPageOpened();
    }

    public void isPageOpened() {
        waitForVisibiltyOfElement(CREATE_PROJECT_BUTTON,"Страница создания нового проекта не открыта", 10);
    }

    public CreateProjectPage fillInFields(ProjectFile project) {
        driver.findElement(NAME_FIELD).sendKeys(project.getName());
        driver.findElement(DESCRIPTION_FIELD).sendKeys(project.getDescription());
        for (int i = 0; i < project.getDomains().size(); i++){
            driver.findElements(DOMAINS_FIELD).get(i).sendKeys(project.getDomains().get(i));
        }
        return this;
    }

    public CreateProjectPage clickCreate() {
        click(driver.findElement(CREATE_PROJECT_BUTTON));
        return this;
    }

}
