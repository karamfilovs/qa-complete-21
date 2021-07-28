package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By loggedUserSelector = By.cssSelector("div.userpanel-header");
    private final String URL = "/home";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getLoggedUser(){
        return getText(loggedUserSelector);
    }

    public void navigate(){
        navigateTo(URL);
    }
}
