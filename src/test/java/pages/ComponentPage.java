package pages;

import models.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.assertEquals;

public class ComponentPage extends BasePage {

    By UPDATE_COMPONENT_BUTTON = By.xpath("//button[contains(text(), 'Update')]");
    By CREATE_COMPONENT_BUTTON = By.xpath("//button[contains(text(), 'Create')]");
    By COMPONENT_TYPE = By.name("type");
    By COMPONENT_NAME = By.name("name");
    By COMPONENT_CODE = By.className("component-code");

    public ComponentPage(WebDriver driver) {
        super(driver);
    }

    public void isPageOpened() {
        waitForVisibiltyOfElement(COMPONENT_TYPE, "Страница создания нового компонента не открыта", 10);
    }

    public ProjectsPage createComponent(Component component) {
        fillInFields(component);
        clickCreateComponent();
        goToParentProject();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        return projectsPage;
    }

    public ProjectsPage editComponent(Component component) {
        updateName(component);
        clickUpdateComponent();
        goToParentProject();
        ProjectsPage projectsPage = new ProjectsPage(driver);
        return projectsPage;
    }

    public ComponentPage fillInFields(Component component) {
        if (component.getComponentName() != null) {
            new Select(driver.findElement(this.COMPONENT_TYPE)).selectByVisibleText(component.getComponentType());
        }
        updateName(component);
        return this;
    }

    public ComponentPage updateName(Component component) {
        driver.findElement(this.COMPONENT_NAME).clear();
        driver.findElement(this.COMPONENT_NAME).sendKeys(component.getComponentName());
        return this;
    }

    public void clickCreateComponent() {
        click(driver.findElement(CREATE_COMPONENT_BUTTON));
    }

    public void clickUpdateComponent() {
        click(driver.findElement(UPDATE_COMPONENT_BUTTON));
    }

    public ProjectsPage goToParentProject() {
        driver.navigate().back();
        driver.navigate().refresh();
        return new ProjectsPage(driver);
    }

    public void checkComponentNameEdited(Component component) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(UPDATE_COMPONENT_BUTTON));
        String componentName = getDisplayedName();
        assertEquals(componentName, component.getComponentName(), "Название компонента не было обновлено"); }

    public String getDisplayedType() {
        return driver.findElement(COMPONENT_TYPE).getAttribute("value");
    }

    public String getDisplayedName() {
        return driver.findElement(COMPONENT_NAME).getAttribute("value");
    }
}