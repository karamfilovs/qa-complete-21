package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.ItemPage;
import pages.LoginPage;
import java.time.Duration;


public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected ItemPage itemPage;


    @BeforeAll
    private static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    private void beforeEachTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        itemPage = new ItemPage(driver);
    }

    @AfterEach
    private void afterEachTest() {
        driver.quit();
    }
}
