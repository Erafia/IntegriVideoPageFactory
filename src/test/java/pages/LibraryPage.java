package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LibraryPage extends BasePage {
    By ICON_NO_ITEMS = By.className("iv-icon iv-icon-file-empty");
    By PROJECTS_IN_LIBRARY = By.xpath("//h3//a");

    public LibraryPage(WebDriver driver) {
        super(driver);
    }

    public void isPageOpened() {
        waitForVisibiltyOfElement(ICON_NO_ITEMS,"Страница библиотеки не открыта", 10);
        //костыль который работает на нас. Потенциально, можно было бы через if - т.е. дождаться или иконки, или любой позиции в библиотеке
    }

    public int getProjectsInLibraryCount(){
        return driver.findElements(PROJECTS_IN_LIBRARY).size();
    }

    public String getProjectsInLibraryName(int numberOfProject){
        return driver.findElements(PROJECTS_IN_LIBRARY).get(numberOfProject-1).getAttribute("value");
    }
}
