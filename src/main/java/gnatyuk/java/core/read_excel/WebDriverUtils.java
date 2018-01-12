package gnatyuk.java.core.read_excel;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverUtils {

    private WebDriverUtils() {
    }

    public static ChromeDriver getChromeWebDriver() {
        System.setProperty("webdriver.chrome.ua.driver", getChromeWebDriverPath());
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(false);
        return new ChromeDriver(chromeOptions);
    }

    public static FirefoxDriver getFirefoxWebDriver() {
        System.setProperty("webdriver.firefox.marionette", getFirefoxWebDriverPath());
        final FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(true);
        return new FirefoxDriver(firefoxOptions);
    }

    private static String getChromeWebDriverPath() {
        return CategoriesGenerator.class.getResource("/driver/chromedriver_32.exe").getFile();
    }

    private static String getFirefoxWebDriverPath() {
        return CategoriesGenerator.class.getResource("/driver/geckodriver.exe").getFile();
    }
}
