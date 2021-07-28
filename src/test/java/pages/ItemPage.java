package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemPage extends BasePage {
    private final String URL = "/objects/manage";
    private By headlineSelector = By.xpath("//div[@id='headline']//h2");
    private By nameSelector = By.name("name");
    private By priceSelector = By.name("price");
    private By saveButtonSelector = By.name("do_submit");
    private By createNewButtonSelector = By.cssSelector("a.newbtn.selenium-add-item");
    private By successMessageSelector = By.id("okmsg");

    public ItemPage(WebDriver driver) {
        super(driver);
    }


    /**
     * Navigates to item page
     */
    public void navigate(){
        navigateTo(URL);
    }

    /**
     * Returns the headline text
     * @return text
     */
    public String getHeadline(){
        return getText(headlineSelector);
    }

    private void enterName(String name){
        type(nameSelector, name);
    }

    private void enterPrice(String price){
        type(priceSelector, price);
    }

    private void clickSaveButton(){
        click(saveButtonSelector);
    }

    private void clickCreateNewItemButton(){
        click(createNewButtonSelector);
    }

    public void createItem(String itemName, String price) {
        clickCreateNewItemButton();
        enterName(itemName);
        enterPrice(price);
        clickSaveButton();
    }

    public String getSuccessMessage() {
        return getText(successMessageSelector);
    }
}
