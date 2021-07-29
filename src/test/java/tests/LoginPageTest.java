package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.Defaults;

public class LoginPageTest extends BaseTest {


    @Test
    @Tag("smoke")
    @Tag("positive")
    @DisplayName("Can login with valid credentials")
    public void canLoginWithValidCredentials() {
        webApp.loginPage().navigate();
        Assertions.assertEquals("QA Ground", webApp.loginPage().getCompanyName());
        webApp.loginPage()
                .enterEmail(Defaults.EMAIL)
                .enterPassword(Defaults.PASSWORD)
                .clickLoginButton();
        Assertions.assertEquals(Defaults.EMAIL, webApp.homePage().getLoggedUser());
    }

    @Test
    @Tag("smoke")
    @DisplayName("Cant login with invalid password")
    public void cantLoginWithInvalidPassword() {
        webApp.loginPage().navigate();
        Assertions.assertEquals("QA Ground", webApp.loginPage().getCompanyName());
        webApp.loginPage()
                .enterEmail(Defaults.EMAIL)
                .enterPassword("Dummy password")
                .clickLoginButton();
        Assertions.assertEquals("Грешно потребителско име или парола. Моля, опитайте отново.", webApp.loginPage().getErrorMessage());
    }

    @Test
    @Tag("smoke")
    @DisplayName("Cant login with invalid email")
    public void cantLoginWithInvalidEmail() {
        webApp.loginPage().navigate();
        Assertions.assertEquals("QA Ground", webApp.loginPage().getCompanyName());
        webApp.loginPage()
                .enterEmail("test@qaground.com")
                .enterPassword("Dummy password")
                .clickLoginButton();
        Assertions.assertEquals("Грешно потребителско име или парола. Моля, опитайте отново.", webApp.loginPage().getErrorMessage());
    }

    @Test
    @Tag("smoke")
    @DisplayName("Cant login with blank credentials")
    public void cantLoginWithBlankCredentials() {
        webApp.loginPage().navigate();
        Assertions.assertEquals("QA Ground", webApp.loginPage().getCompanyName());
        webApp.loginPage().clickLoginButton();
        Assertions.assertEquals("Моля, попълнете вашия email", webApp.loginPage().getErrorMessage());
    }
}
