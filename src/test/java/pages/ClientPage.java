package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClientPage extends BasePage {
    private final String URL = "/clients/manage";
    private final By headlineSelector = By.xpath("//div[@id='headline2']//h2");
    private final By newButtonSelector = By.xpath("//div[@id='headline2']//a[@class='newbtn selenium-add-client-button']");
    private final By firmNameField = By.name("firm_name");
    private final By firmAddressField = By.name("firm_addr");
    private final By firmTownField = By.name("firm_town");
    private final By successMessage = By.id("okmsg");
    private final By createClientSelector = By.name("do_submit");
    private final By errorMessage = By.id("error");
    private final By personRadioButton = By.id("type_person");
    private final By firmRadioButton = By.id("type_firm");
    private final By personNameField = By.name("person_name");
    private final By personAddressField = By.name("person_address");
    private final By personTownField = By.name("person_city");

    /**
     * Setup your driver
     * @param driver browser
     */
    public ClientPage(WebDriver driver){
        super(driver);
    }

    /**
     * Navigate to the clients page
     */
    public void navigate() {
        navigateTo(URL);
    }

    /**
     * Get the headline of the clients page
     * @return text
     */
    public String getHeadline() {
        return getText(headlineSelector);
    }

    /**
     * Click on create new client button
     */
    public void clickOnCreateNewClientButton() {
        click(newButtonSelector);
    }

    /**
     * Click on person radio button
     */
    public void clickOnPersonRadioButton() {
        click(personRadioButton);
    }

    public void enterPersonName(String name) {
        type(personNameField, name);
    }

    public void enterPersonAddress(String address) {
        type(personAddressField, address);
    }

    public void enterPersonTown(String town) {
        type(personTownField, town);
    }

    /**
     * Click on firm radio button (it is clicked by default when you get to add client page)
     */
    public void clickOnFirmRadioButton() {
        click(firmRadioButton);
    }

    /**
     * Enter firm name
     * @param name text
     */
    public void enterFirmName(String name) {
        type(firmNameField, name);
    }

    /**
     * Enter address for firm
     * @param address text
     */
    public void enterFirmAddress(String address) {
        type(firmAddressField, address);
    }

    /**
     * Enter firm town
     * @param town text
     */
    public void enterFirmTown(String town) {
        type(firmTownField, town);
    }

    /**
     * Click on the add client button
     */
    public void clickOnAddClientButton() {
        click(createClientSelector);
    }

    /**
     * Create new firm
     * @param name text
     * @param address text
     * @param town text
     */
    public void createFirmWithValidCredentials(String name, String address, String town) {
        clickOnCreateNewClientButton();
        enterFirmName(name);
        enterFirmAddress(address);
        enterFirmTown(town);
        clickOnAddClientButton();
    }

    /**
     * Create firm with missing town field
     * @param name text
     * @param address text
     */
    public void createFirmWithMissingTown(String name, String address) {
        clickOnCreateNewClientButton();
        enterFirmName(name);
        enterFirmAddress(address);
        clickOnAddClientButton();
    }

    /**
     * Create person with valid credentials
     * @param name text
     * @param address text
     * @param town text
     */
    public void createPersonWithValidCredentials(String name, String address, String town) {
        clickOnCreateNewClientButton();
        clickOnPersonRadioButton();
        enterPersonName(name);
        enterPersonAddress(address);
        enterPersonTown(town);
        clickOnAddClientButton();
    }

    /**
     * Get the successful message for the creation
     * @return text
     */
    public String getSuccessMessage() {
        return getText(successMessage);
    }

    /**
     * Get error message text
     * @return text
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

}
