package pages;

import org.openqa.selenium.WebDriver;

public class ClientPage extends BasePage {
    private final String URL = "/clients/manage";
    public ClientPage(WebDriver driver){
        super(driver);
    }
}
