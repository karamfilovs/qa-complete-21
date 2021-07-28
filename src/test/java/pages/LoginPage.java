package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Constants;

import java.util.concurrent.ConcurrentSkipListMap;

public class LoginPage extends BasePage {
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
        enterEmail(Constants.EMAIL);
        enterPassword(Constants.PASSWORD);
        clickLoginButton();
    }

    public void enterEmail(String email) {
        type(emailSelector, email);
    }

    public void enterPassword(String password) {
        type(passwordSelector, password);
    }

    public void clickLoginButton(){
        click(loginButtonSelector);
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

    public void navigate(){
        navigateTo(URL);
    }
}
