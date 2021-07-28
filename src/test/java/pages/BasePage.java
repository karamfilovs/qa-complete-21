package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Defaults;

public class BasePage {
    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void type(By selector, String text) {
        System.out.println("Typing text:" + text);
        driver.findElement(selector).sendKeys(text);
    }

    protected void click(By selector) {
        System.out.println("Clicking element");
        driver.findElement(selector).click();
    }

    protected String getText(By selector) {
        waitForVisibilityOf(selector);
        System.out.println("Retrieving text from element");
        //Sync logic (explicit wait)
        return driver.findElement(selector).getText().trim();
    }

    private void waitForVisibilityOf(By selector){
        //Will be implemented next time
    }

    private void waitForElementToBeActive(By selector){
        //Will be implemented next time
    }

    private void waitForElementToBeClickable(By selector){
        //Will be implemented next time
    }

    protected void scroll(By selector) {
    }

    protected void navigateTo(String url){
        System.out.println("Navigating to: " + Defaults.BASE_URL + url);
        driver.navigate().to(Defaults.BASE_URL + url);
    }
}
