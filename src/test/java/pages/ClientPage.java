package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Defaults;

public class ClientPage extends BasePage {

    private static final String URL = "/clients/manage";
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientPage.class);
    private static final By SEARCH_MENU_BUTTON = By.id("searchbtn");
    private static final By HEADER = By.cssSelector("#headline2>h2");
    private static final By FIRM_NAME_INPUT_FIELD = By.name("fnm");
    private static final By SEARCH_RESULTS_TABLE = By.id("fakturi_table");
    private static final By SEARCH_BUTTON = By.name("s");

    public ClientPage(WebDriver driver){
        super(driver);
    }

    public ClientPage navigate(){
        LOGGER.info("Navigating to: " + Defaults.BASE_URL + URL);
        navigateTo(URL);
        return this;
    }

    public String getHeader(){
        LOGGER.info("Retrieving header text");
        return getText(HEADER);
    }

    public ClientPage clickExpandSearchMenuButton(){
        LOGGER.info("Expanding search field");
        click(SEARCH_MENU_BUTTON);
        return this;
    }

    public ClientPage clickSearchButton(){
        LOGGER.info("Clicking search button");
        click(SEARCH_BUTTON);
        return this;
    }

    public ClientPage enterFirmNameToSearchFor(String firmName){
        LOGGER.info("Entering firm name to search for: " + firmName);
        type(FIRM_NAME_INPUT_FIELD, firmName);
        return this;
    }

    public String getTextFromSearchResultsTable(){
        LOGGER.info("Retrieving text from results table");
        return getText(SEARCH_RESULTS_TABLE);
    }
}
