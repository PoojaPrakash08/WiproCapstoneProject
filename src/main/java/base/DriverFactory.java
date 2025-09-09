package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DriverFactory {
    private static WebDriver driver;
    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    public static WebDriver initDriver() {
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("user-data-dir=C:\\Users\\Pooja\\AppData\\Local\\Google\\Chrome\\User Data");
    	options.addArguments("--profile-directory=Default"); // or your profile name
    	options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-gpu");
        
        if (driver == null) {
        	log.info("Launching Chrome browser");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
        	log.info("Quitting the browser");
            driver.quit();
            driver = null;
        }
    }
}
