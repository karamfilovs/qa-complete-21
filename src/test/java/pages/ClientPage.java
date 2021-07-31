package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientPage extends BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientPage.class);
    private final String URL = "/clients/manage";

    private By clientsHeaderButtonSelector = By.id("tabs_clients");
    private By headlineSelector = By.xpath("//div[@id='headline2']//h2");
    private By searchExpandButtonSelector = By.id("searchbtn");
    private By searchClientNameFieldSelector = By.name("fnm");
    private By searchClientAddressFieldSelector = By.name("adr");
    private By searchClientMolFieldSelector = By.name("mol");
    private By emptyResultListSelector = By.id("emptylist");
    private By searchResultTableSelector = By.id("fakturi_table");
    private By searchButtonSelector = By.name("s");

    public ClientPage(WebDriver driver){
        super(driver);
    }

    /**
     * Navigates to clients page
     */
    public ClientPage navigate() {
        LOGGER.info("Navigating to url:" + URL);
        navigateTo(URL);
        return this;
    }

    /**
     * Getting the headline text
     * @return text
     */
    public String getHeadline() {
        return getText(headlineSelector);
    }

    /**
     * Clicking expand search menu button
     */
    public ClientPage clickSearchExpandButton() {
        LOGGER.info("Expanding Search section");
        click(searchExpandButtonSelector);
        return this;
    }

    /**
     * Typing in the name field of the expanded search menu
     * @param name name
     */

    public ClientPage enterNameToSearch(String name) {
        LOGGER.info("Entering name to search for:" + name);
        type(searchClientNameFieldSelector, name);
        return this;
    }

    /**
     * Clicking search button
     */
    public ClientPage clickSearchButton() {
        click(searchButtonSelector);
        return this;
    }

    /**
     * Get text if there is no clients
     * @return text
     */
    public String getEmptyListMessage() {
        return getText(emptyResultListSelector);
    }

    /**
     * Get the results of the search table
     * @return text
     */
    public String getResultTableInfo(){
        return getText(searchResultTableSelector);
    }
}
