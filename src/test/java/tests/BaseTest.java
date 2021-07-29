package tests;

import api.HTTPClient;
import core.API;
import core.BrowserFactory;
import core.WebApp;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Defaults;


public class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    private WebDriver driver;
    private BrowserFactory browserFactory;
    protected WebApp webApp;
    protected API api;


    @BeforeAll
    private static void beforeAll() {
        HTTPClient.setupRequestDefaults();
        downloadDriver(Defaults.BROWSER);
    }

    @BeforeEach
    private void beforeEachTest(TestInfo info) {
        LOGGER.info("STARTING TEST: " + info.getDisplayName());
        browserFactory = new BrowserFactory();
        driver = browserFactory.startBrowser(Defaults.BROWSER);
        webApp = new WebApp(driver);
        api = new API();
    }

    @AfterEach
    private void afterEachTest() {
        driver.quit();
    }

    private static void downloadDriver(String driverType) {
        switch (driverType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                break;
            default: throw new IllegalArgumentException("Not supported browser!");
        }
    }
}
