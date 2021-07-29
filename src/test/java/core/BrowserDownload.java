package core;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserDownload {
    public static void downloadDriver(String driverType) {
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
