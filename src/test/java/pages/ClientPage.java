package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Defaults;

import java.util.Random;

public class ClientPage extends BasePage {
    private final String URL = "/clients/manage";
    private By headLineSelector = By.xpath("//div[@id='headline2']/h2");
    private By expandSearchButtonSelector = By.xpath("//a[@id='searchbtn']");
    private By searchNameFieldSelector = By.name("fnm");
    private By searchButtonSelector = By.name("s");
    private By searchResultTableSelector = By.xpath("//table[@id='fakturi_table']");
    private By emptyResultListSelector = By.xpath("//div[@id='emptylist']");
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientPage.class);



    public ClientPage(WebDriver driver) {
        super(driver);
    }


    public ClientPage navigate() {
        LOGGER.info("Navigating to url:" + Defaults.BASE_URL + URL);
        navigateTo(URL);
        return this;
    }

    public String getHeadline() {
        {
            return getText(headLineSelector);
        }

    }

    public ClientPage expandSearchSection() {
        LOGGER.info("Expanding Search section");
        click(expandSearchButtonSelector);
        return this;
    }

    public ClientPage enterNameToSearchFor(String clientToSearch) {
        LOGGER.info("Entering name to search for:" + clientToSearch);
        type(searchNameFieldSelector, clientToSearch);
        return this;
    }

    public ClientPage clickSearchButton() {
        LOGGER.info("Clicking search button:");
        click(searchButtonSelector);
        return this;
    }

    public String getResultTableInfo() {
        return getText(searchResultTableSelector);
    }

    public String getEmptyListMessage() {
        return getText(emptyResultListSelector);
    }

}






