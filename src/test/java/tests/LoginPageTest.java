package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.Constants;

public class LoginPageTest extends BaseTest {


    @Test
    @Tag("smoke")
    @Tag("positive")
    @DisplayName("Can login with valid credentials")
    public void canLoginWithValidCredentials() {
        loginPage.navigate();
        Assertions.assertEquals("QA Ground", loginPage.getCompanyName());
        loginPage.enterEmail(Constants.EMAIL);
        loginPage.enterPassword(Constants.PASSWORD);
        loginPage.clickLoginButton();
        Assertions.assertEquals(Constants.EMAIL, homePage.getLoggedUser());
    }

    @Test
    @Tag("smoke")
    @DisplayName("Cant login with invalid password")
    public void cantLoginWithInvalidPassword() {
        loginPage.navigate();
        Assertions.assertEquals("QA Ground", loginPage.getCompanyName());
        loginPage.enterEmail(Constants.EMAIL);
        loginPage.enterPassword("Dummy password");
        loginPage.clickLoginButton();
        Assertions.assertEquals("Грешно потребителско име или парола. Моля, опитайте отново.", loginPage.getErrorMessage());
    }

    @Test
    @Tag("smoke")
    @DisplayName("Cant login with invalid email")
    public void cantLoginWithInvalidEmail() {
        loginPage.navigate();
        Assertions.assertEquals("QA Ground", loginPage.getCompanyName());
        loginPage.enterEmail("test@qaground.com");
        loginPage.enterPassword("Dummy password");
        loginPage.clickLoginButton();
        Assertions.assertEquals("Грешно потребителско име или парола. Моля, опитайте отново.", loginPage.getErrorMessage());
    }

    @Test
    @Tag("smoke")
    @DisplayName("Cant login with blank credentials")
    public void cantLoginWithBlankCredentials() {
        loginPage.navigate();
        Assertions.assertEquals("QA Ground", loginPage.getCompanyName());
        loginPage.clickLoginButton();
        Assertions.assertEquals("Моля, попълнете вашия email", loginPage.getErrorMessage());
    }
}
