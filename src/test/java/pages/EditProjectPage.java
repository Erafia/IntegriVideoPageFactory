package pages;

import models.ProjectFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditProjectPage extends CreateProjectPage {

    By UPDATE_PROJECT_BUTTON = By.xpath("//button[contains(text(), 'Update')]");
    By DISPLAYED_PROJECT_NAME = By.xpath("//h1");
    By DISPLAYED_PROJECT_DESCRIPTION = By.xpath("//div[@class = 'col-12 description']");


    public EditProjectPage(WebDriver driver) {
        super(driver);
    }

    public void isPageOpened() {
        waitForVisibiltyOfElement(UPDATE_PROJECT_BUTTON, "Страница создания нового проекта не открыта", 10);
    }

    public ProjectsPage editProject(ProjectFile project) {
        isPageOpened();
        fillInFields(project);
        clickUpdate();
        ProjectsPage projectPage = new ProjectsPage(driver);
        return projectPage;
    }

    public EditProjectPage fillInFields(ProjectFile project) {
        driver.findElement(this.NAME_FIELD).clear();
        driver.findElement(this.DESCRIPTION_FIELD).clear();
        driver.findElement(this.NAME_FIELD).sendKeys(project.getName());
        driver.findElement(this.DESCRIPTION_FIELD).sendKeys(project.getDescription());
        for (int i = 0; i < project.getDomains().size(); i++) {
            driver.findElements(this.DOMAINS_FIELD).get(i).clear();
        }
        for (int i = 0; i < project.getDomains().size(); i++) {
            driver.findElements(this.DOMAINS_FIELD).get(i).sendKeys(project.getDomains().get(i));
        }
        return this;
    }

    public void clickUpdate() {
        click(driver.findElement(UPDATE_PROJECT_BUTTON));
    }

    public String getDisplayedName() {
        return driver.findElement(this.DISPLAYED_PROJECT_NAME).getText();
    }

    public String getDisplayedDescription() {
        return driver.findElement(this.DISPLAYED_PROJECT_DESCRIPTION).getText();
    }
}
