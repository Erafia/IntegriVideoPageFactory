package tests;

import models.BillingCard;
import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BillingPage;
import pages.LoginPage;
import java.util.Calendar;
import static org.testng.Assert.assertEquals;

public class BillingCardTest extends BaseTest{
    String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR)+1);
    LoginPage loginpage;
    BillingPage billingPage;
    User user = new User("verySecure", "integri_Erafia@mailinator.com");
    BillingCard card = new BillingCard("4444333322221111", "11", year, "Name Namovich");



    @BeforeMethod
    public void openProjectsPage() {
        loginpage = new LoginPage(driver);
        loginpage.openPage();
        loginpage.login(user);
        billingPage = new BillingPage(driver);
        billingPage.openPage();
    }

    @Test
    public void createCard(){
        int initialCardNumber = billingPage.getCardsCount();
        billingPage.clickAddNewCard();
        int finalCardNumber = billingPage.addBillingCard(card)
                                         .getCardsCount();
        assertEquals(finalCardNumber,initialCardNumber+1, "Новая карта не добавлена в биллинг");
    }

    @Test
    public void removeCard(){
        billingPage.clickAddNewCard();
        int initialCardNumber = billingPage.getCardsCount();
        int finalCardNumber = billingPage.removeLastBilling(1).getCardsCount();
        assertEquals(finalCardNumber,initialCardNumber-1, "Карта не была удалена");
    }

    @Test
    public void checkOnlyOneDefaultCardCanBe(){
        billingPage.clickAddNewCard().addBillingCard(card);
        billingPage.makeBillingDefault(1);
        /*считаем, что уже есть как минимум 1 карта(createCard test), но как в таком случае выполнять тест изолированно?
        Создавать метод, который штампует заказанное количество карт? Или просто дважды вызвать создание карты?
         */
        assertEquals(billingPage.getDefaultBillingsCount(),1, "Возможно сделать несколько карт основными");
    }

}
