package tests;

import models.User;
import org.testng.annotations.Test;
import pages.LoginPage;

public class SignUpTests extends BaseTest {
    User user = new User("em", "pas");
    @Test
    public void signUP(){
        new LoginPage(driver)
                .openPage();
              //  .login(user);

    }
}
