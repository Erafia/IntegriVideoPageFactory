package pages;

import models.ProjectFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ProjectsPage extends BasePage {
    By ADD_PROJECT_BUTTON = By.xpath("//div[text()='Add project']");
    By EDIT_PROJECT_BUTTON = By.xpath("//a[contains(text(), 'Edit')]");
    By PROJECT = By.className("project");
    By COMPONENT = By.className("component");
    By ADD_COMPONENT_BUTTON = By.xpath("//div[@class = 'component new']//a");

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://dev.integrivideo.com/app/projects");
        isPageOpened();
    }

    public void isPageOpened() {
        waitForVisibiltyOfElement(ADD_PROJECT_BUTTON, "Страница проектов не открыта", 10);
    }

    public ProjectsPage createProject(ProjectFile project) {
        click(driver.findElement(ADD_PROJECT_BUTTON));
        CreateProjectPage createPage = new CreateProjectPage(driver);
        createPage.isPageOpened();
        createPage
                .fillInFields(project)
                .clickCreate();
        return this;
    }

    public int getProjectsCount() {
        this.isPageOpened();
        return driver.findElements(PROJECT).size();
    }

    public ProjectsPage goToProjectPage(int numOfProject) {
        this.isPageOpened();
        List<WebElement> projects = driver.findElements(PROJECT);
        int actualNumOfProjects = projects.size();
        if (actualNumOfProjects < numOfProject) {
            System.out.println("Запрашиваемого проекта не существует");
        }
        try {
            projects.get(numOfProject - 1).click();
        } catch (NullPointerException e) {
            System.out.println("Проект недоступен для нажатия");
        }
        return this;
    }

    public EditProjectPage clickEditProjectButton() {
        click(driver.findElement(EDIT_PROJECT_BUTTON));
        EditProjectPage editProjectPage = new EditProjectPage(driver);
        editProjectPage.isPageOpened();
        return editProjectPage;
    }

    public ComponentPage clickAddComponentButton() {
        click(driver.findElement(ADD_COMPONENT_BUTTON));
        ComponentPage componentPage = new ComponentPage(driver);
        componentPage.isPageOpened();
        return componentPage;
    }

    public void checkProjectFieldsEdited(ProjectFile project) {
        EditProjectPage editProjectPage = new EditProjectPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_COMPONENT_BUTTON));
        String description = editProjectPage.getDisplayedDescription();
        String name = editProjectPage.getDisplayedName();
        assertEquals(name, project.getName(), "Название проекта не было обновлено");
        assertEquals(description, project.getDescription(), "Описание компонента не было обновлено");
    }

    public ComponentPage goToComponent(int numOfComponent) {
        ComponentPage componentPage = new ComponentPage(driver);
        List<WebElement> components = driver.findElements(COMPONENT);
        int actualnumOfComponents = components.size();
        if (actualnumOfComponents < numOfComponent) {
            System.out.println("Запрашиваемого компонента не существует");
        }
        try {
            components.get(numOfComponent - 1).click();
        } catch (NullPointerException e) {
            System.out.println("Компонент недоступен для нажатия");
        }
        return componentPage;
    }

    public int getComponentsCount() {
        return driver.findElements(COMPONENT).size();
    }

}
