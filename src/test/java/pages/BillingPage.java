package pages;

import models.BillingCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BillingPage extends BasePage {
    By ADD_NEW_BILLING_BUTTON = By.xpath("//a[text()='Add new']");
    By ADD_BUTTON = By.xpath("//button[text()='Add']");
    By CARD_NUMBER_FIELD = By.name("number");
    By CARD_YEAR_FIELD = By.name("expirationYear");
    By CARDHOLDER_NAME_FIELD = By.name("cardholderName");
    By DISPLAYED_CARDS = By.xpath("//div[@class = 'cards']//div[@class = 'row']");
    By MAKE_DEFAULT_BUTTON = By.xpath("//a[contains(text(),'Make default')]");
    By CARD_MONTH_FIELD = By.name("expirationMonth");
    By DEFAULT_LABEL = By.xpath("//div[contains(text(),'Default')]");
    By DEFAULT_MESSAGE = By.xpath("//span[contains(text(), 'Default payment method successfully changed')]");
    By REMOVE_BUTTONS = By.xpath("//a[contains(text(),'Remove')]");
    By REMOVED_MESSAGE = By.xpath("//span[contains(text(), 'Payment method successfully removed')]");


    public BillingPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://dev.integrivideo.com/app/billing");
        isPageOpened();
    }


    public void isPageOpened() {
        waitForVisibiltyOfElement(ADD_NEW_BILLING_BUTTON, "Страница биллинга не открыта", 20);
    }

    public BillingPage addBillingCard(BillingCard card) {
        driver.findElement(CARD_NUMBER_FIELD).sendKeys(card.getCardNumber());
        driver.findElement(CARD_MONTH_FIELD).sendKeys(card.getMonth());
        driver.findElement(CARD_YEAR_FIELD).sendKeys(card.getYear());
        driver.findElement(CARDHOLDER_NAME_FIELD).sendKeys(card.getCardHolderName());
        wait.until(ExpectedConditions.elementToBeClickable(ADD_BUTTON)).click();
        //Здесь тест проходит только в дебаг-режиме. Сколько ни выставляй таймаут - кнопка почему-то не отрабатывает.
        return this;
    }

    public BillingPage clickAddNewCard() {
        click(driver.findElement(ADD_NEW_BILLING_BUTTON));
        return this;
    }

    public BillingPage makeBillingDefault(int numOfCard) {
        List<WebElement> defaultButtons = driver.findElements(MAKE_DEFAULT_BUTTON);
        int actualNumOfNotDefaultCards = defaultButtons.size();
        if (actualNumOfNotDefaultCards < numOfCard) {
            System.out.println("Существует менее двух карт. Нельзя сменить основную карту");
        }
        try {
            defaultButtons.get(numOfCard - 1).click();
            waitForVisibiltyOfElement(DEFAULT_MESSAGE, "Не удалось выбрать новую основную карту", 10);
        } catch (NullPointerException e) {
            System.out.println("Такой кнопки не существует");
        }
        return this;
    }

    public BillingPage removeLastBilling(int numOfCard) {
        List<WebElement> removeButtons = driver.findElements(REMOVE_BUTTONS);
        click(removeButtons.get(numOfCard -1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(REMOVED_MESSAGE));
        return this;
    }

    public int getCardsCount() {
        isPageOpened();
        return driver.findElements(DISPLAYED_CARDS).size();
    }

    public int getDefaultBillingsCount() {
        return driver.findElements(DEFAULT_LABEL).size();
    }


}
