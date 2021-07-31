package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Defaults;

public class LoginPage extends BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);
    private final String URL = "/login";
    private By emailSelector = By.id("loginusername");
    private By passwordSelector = By.id("loginpassword");
    private By loginButtonSelector = By.id("loginsubmit");
    private By errorMessageSelector = By.id("error");
    private By companyNameSelector = By.xpath("//div[@id='wellcome']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void defaultLogin(){
        navigate();
        enterEmail(Defaults.EMAIL);
        enterPassword(Defaults.PASSWORD);
        clickLoginButton();
    }

    public LoginPage enterEmail(String email) {
        LOGGER.info("Entering email:" + email);
        type(emailSelector, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        LOGGER.info("Entering password:" + password);
        type(passwordSelector, password);
        return this;
    }

    public LoginPage clickLoginButton(){
        LOGGER.info("Click Login button");
        click(loginButtonSelector);
        return this;
    }

    public String getErrorMessage(){
        return getText(errorMessageSelector);
    }

    public String getCompanyName(){
        return getText(companyNameSelector);
    }

    public void scrollToLoginButton(){
        scroll(loginButtonSelector);
    }

    public LoginPage navigate(){
        LOGGER.info("Navigating to:" + URL);
        navigateTo(URL);
        return this;
    }
}
